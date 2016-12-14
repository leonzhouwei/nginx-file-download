package com.example.webgui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.common.Sha2Encoder;
import com.example.config.AppConfig;
import com.example.domain.Account;
import com.example.filter.LoginInterceptor;
import com.example.persist.must.AccountRMapper;
import com.example.webapi.RouteDefine;

@Controller
public class WebGuiLoginController {

	static final String PASSWORD = "username";
	static final String USERNAME = "password";

	static final String VIEW_NAME_LOGIN = "login";

	private static final Logger logger = LoggerFactory
			.getLogger(WebGuiLoginController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper rMapper;

	@RequestMapping(value = RouteDefine.LOGIN, method = RequestMethod.GET)
	public ModelAndView gotoLoginPage() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LOGIN);
	}

	@RequestMapping(value = RouteDefine.LOGIN, method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (LoginInterceptor.sessionIdExist(request)) {
			response.sendRedirect(RouteDefine.FILES);
			logger.info(LoginInterceptor.getSessionId(request)
					+ " has already signed in");
			return;
		}
		String username = request.getParameter(USERNAME);
		String plain = request.getParameter(PASSWORD);
		String cypher = Sha2Encoder.encode(plain);
		Account e = new Account();
		e.setName(username);
		e.setPassword(cypher);
		Account account = rMapper.selectEnabledByNameAndPassword(e);
		if (!Account.isValidAccount(account)) {
			response.sendRedirect(RouteDefine.ROOT);
			return;
		}
		LoginInterceptor.setSessionId(request, account.getId());
		logger.info(username + " signed in OK");
		response.sendRedirect(RouteDefine.ROOT);
	}

	@RequestMapping(value = RouteDefine.LOGOUT)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (LoginInterceptor.sessionIdExist(request)) {
			LoginInterceptor.removeSessionId(request);
		}
		response.sendRedirect(RouteDefine.LOGIN);
	}

}
