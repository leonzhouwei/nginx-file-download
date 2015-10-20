package com.example.webgui;

import org.springframework.web.servlet.ModelAndView;

public class WebGuiNotFoundController {
	
	static ModelAndView newModelAndView() {
		return new ModelAndView("common/404");
	}

}
