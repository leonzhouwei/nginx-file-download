package com.example.webgui;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.Account;
import com.example.filter.LoginInterceptor;
import com.example.persist.must.AccountRMapper;
import com.example.webapi.RouteDefine;

@Controller
public class IndexController {

	static final String ASSETS_VERSION = "ASSETS_VERSION";

	static final String VIEW_NAME_INDEX = "index";
	static final String VIEW_NAME_ADMIN_INDEX = "admin/" + VIEW_NAME_INDEX;

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper accoutRMapper;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = LoginInterceptor.getAccountId(request);
		if (id == null) {
			response.sendRedirect(RouteDefine.FILES);
			return null;
		}
		
		Account account = accoutRMapper.selectEnabledById(id);
		if (Account.isAdmin(account)) {
			return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_ADMIN_INDEX);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_INDEX);
		return ret;
	}

}
