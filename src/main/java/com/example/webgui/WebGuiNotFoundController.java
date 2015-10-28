package com.example.webgui;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpServletResponseUtil;

public class WebGuiNotFoundController {
	
	static final String COMMON_404 = "common/404";
	
	static ModelAndView newModelAndView() {
		return new ModelAndView(COMMON_404);
	}
	
	static ModelAndView newModelAndView(HttpServletResponse response) {
		HttpServletResponseUtil.setStatusAsNotFound(response);
		return newModelAndView();
	}

}
