package com.example.webgui;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpServletResponseUtil;

public class WebGuiNotFoundController {
	
	static ModelAndView newModelAndView() {
		return new ModelAndView("common/404");
	}
	
	static ModelAndView newModelAndView(HttpServletResponse response) {
		HttpServletResponseUtil.setStatusAsNotFound(response);
		return newModelAndView();
	}

}
