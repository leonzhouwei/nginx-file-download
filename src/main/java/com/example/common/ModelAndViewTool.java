package com.example.common;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.config.AppConfig;

public final class ModelAndViewTool {

	static final String APP_ASSETS_HOME = "APP_ASSETS_HOME";
	static final String COMMON_404 = "common/404";

	private ModelAndViewTool() {
	}

	public static ModelAndView newModelAndView(AppConfig appConfig,
			String viewName) {
		ModelAndView ret = new ModelAndView(viewName);
		ret.getModel().put(APP_ASSETS_HOME, appConfig.getAssetsHome());
		return ret;
	}

	public static ModelAndView newModelAndViewFor404(AppConfig appConfig,
			HttpServletResponse response) {
		HttpResponseTool.setStatusAsNotFound(response);
		return newModelAndView(appConfig, COMMON_404);
	}
	
	public static ModelAndView newModelAndViewFor404(AppConfig appConfig,
			HttpServletResponse response, String message) {
		HttpResponseTool.setStatusAsNotFound(response, message);
		return newModelAndView(appConfig, COMMON_404);
	}

	public static ModelAndView newModelAndViewAndRedirect(AppConfig appConfig,
			String url) {
		ModelAndView ret = new ModelAndView(new RedirectView(url));
		ret.getModel().put(APP_ASSETS_HOME, appConfig.getAssetsHome());
		return ret;
	}

}
