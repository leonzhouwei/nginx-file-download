package com.example.web.gui.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpRequestTool;
import com.example.common.ModelAndViewTool;
import com.example.common.Sha2Encoder;
import com.example.config.AppConfig;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.persist.must.AccountWMapper;
import com.example.web.consts.RouteDefine;
import com.example.web.gui.WebGuiDefine;
import com.google.common.base.Strings;

@Controller
public class AdminAccountController {

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN + "/account/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX + WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX + WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;
	static final String VIEW_NAME_ADMIN_ACCOUNTS_EDIT_PASSWORD = VIEW_NAME_PREFIX + "edit-pswd";

	static final String PSWD = "password";
	static final String ROLE = "role";

	static final String BASE_ROUTE = RouteDefine.ADMIN + "/accounts";

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper rMapper;
	@Autowired
	private AccountWMapper wMapper;

	@RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/accounts/new", method = RequestMethod.GET)
	public ModelAndView gotoNew(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = BASE_ROUTE, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request) {
		String name = HttpRequestTool.extractName(request);
		Long role = HttpRequestTool.extractLong(request, ROLE);
		String password = request.getParameter(PSWD);
		String cipher = Sha2Encoder.encode(password);
		Account account = new Account();
		account.reset();
		account.setName(name);
		account.setPassword(cipher);
		account.getRole().setId(role);
		wMapper.insert(account);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/accounts/edit", method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		Account account = rMapper.selectById(id);
		if (account == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_EDIT, account);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/accounts/edit", method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		Account account = rMapper.selectById(id);
		if (account == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		String name = HttpRequestTool.extractName(request);
		String rawPassword = request.getParameter(PSWD);
		if (!Strings.isNullOrEmpty(rawPassword)) {
			String cipher = Sha2Encoder.encode(rawPassword);
			account.setPassword(cipher);
		}
		Long role = HttpRequestTool.extractLong(request, ROLE);
		Boolean enabled = HttpRequestTool.extractEnabled(request);
		account.setName(name);
		account.getRole().setId(role);
		account.resetUpdatedAt();
		account.setEnabled(enabled);
		wMapper.update(account);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/accounts/edit-pswd", method = RequestMethod.GET)
	public ModelAndView gotoEditPassword(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		Account account = rMapper.selectById(id);
		if (account == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_ADMIN_ACCOUNTS_EDIT_PASSWORD, account);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/accounts/edit-pswd", method = RequestMethod.POST)
	public ModelAndView editPassword(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		Account account = rMapper.selectById(id);
		if (account == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		String newRawPassword = request.getParameter(PSWD);
		String newPassword = Sha2Encoder.encode(newRawPassword);
		account.setPassword(newPassword);
		account.resetUpdatedAt();
		wMapper.updatePassword(account);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

}
