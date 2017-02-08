package com.example.webgui;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.webapi.RouteDefine;

@Controller
public class DownloadTaskController {

	static final String VIEW_NAME_PREFIX = "dld-task/";
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = RouteDefine.I + "/dld-tasks", method = RequestMethod.GET)
	public ModelAndView getAll(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_LIST);
	}

}
