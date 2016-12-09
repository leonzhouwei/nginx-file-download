package com.example.webgui.admin;

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
import com.example.webapi.RouteDefine;
import com.example.webgui.WebGuiDefine;

@Controller
public class AdminAccountController {

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN + "/account/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX + WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX + WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountWMapper wMapper;
	@Autowired
	private AccountRMapper rMapper;

	@RequestMapping(value = RouteDefine.ADMIN_ACCOUNTS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_ACCOUNTS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_ACCOUNTS, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request, HttpServletResponse response) {
		String name = HttpRequestTool.extractName(request);
		Long role = HttpRequestTool.extractLong(request, "role");
		String password = request.getParameter("password");
		String cipher = Sha2Encoder.encode(password);
		Account account = new Account();
		account.reset();
		account.setName(name);
		account.setPassword(cipher);
		account.setRoleId(role);
		wMapper.insert(account);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig, RouteDefine.ADMIN_ACCOUNTS);
	}

	@RequestMapping(value = RouteDefine.ADMIN_ACCOUNTS_EDIT, method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		Account account = rMapper.selectById(id);
		if (account == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_EDIT, account);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_ACCOUNTS_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		Account account = rMapper.selectById(id);
		if (account == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String name = HttpRequestTool.extractName(request);
		String rawPassword = request.getParameter("password");
		String cipher = Sha2Encoder.encode(rawPassword);
		Long role = HttpRequestTool.extractLong(request, "role");
		Boolean enabled = HttpRequestTool.extractEnabled(request);
		account.setName(name);
		account.setPassword(cipher);
		account.setRoleId(role);
		account.resetUpdatedAt();
		account.setEnabled(enabled);
		wMapper.update(account);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig, RouteDefine.ADMIN_ACCOUNTS);
	}

}
