package com.example.web.gui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.web.consts.RouteDefine;
import com.example.web.interceptor.LoginInterceptor;

@Controller
public class IndexController {

	static final String ASSETS_VERSION = "ASSETS_VERSION";

	static final String VIEW_NAME_INDEX = "index";
	static final String VIEW_NAME_ADMIN_INDEX = "admin/" + VIEW_NAME_INDEX;

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper accoutRMapper;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) throws IOException {
		Long id = LoginInterceptor.getAccountId(request);
		if (id == null) {
			// TODO
			logger.warn("no account id found in session");
			return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, RouteDefine.FILES);
		}

		Account account = accoutRMapper.selectEnabledById(id);
		if (account == null) {
			// TODO
			StringBuilder sb = new StringBuilder();
			sb.append("account with id ");
			sb.append(id);
			sb.append(" does not exist");
			logger.warn(sb.toString());
			return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, RouteDefine.FILES);
		}

		if (Account.isAdmin(account)) {
			return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_ADMIN_INDEX);
		}

		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_INDEX);
	}

}
