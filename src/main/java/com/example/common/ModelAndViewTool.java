package com.example.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.config.AppConfig;
import com.example.filter.LoginInterceptor;


public final class ModelAndViewTool {

	static final String APP_ASSETS_HOME = "APP_ASSETS_HOME";
	static final String COMMON_404 = "common/404";
	static final String LOCALE = "locale";

	private static final Logger logger = LoggerFactory.getLogger(ModelAndViewTool.class);

	private ModelAndViewTool() {
	}

	public static void fill(ModelAndView mav, HttpServletRequest request, AppConfig appConfig) {
		mav.getModel().put(APP_ASSETS_HOME, appConfig.getAssetsHome());
		String locale = LoginInterceptor.getSessionLocale(request);
		logger.debug("locale is null? " + (locale == null));
		if (locale != null) {
			mav.getModel().put(LOCALE, locale);
		}
	}

	public static ModelAndView newModelAndView(HttpServletRequest request, AppConfig appConfig, String viewName) {
		ModelAndView ret = new ModelAndView(viewName);
		fill(ret, request, appConfig);
		return ret;
	}

	public static <T> ModelAndView newModelAndView(HttpServletRequest request, AppConfig appConfig, String viewName,
			T t) {
		ModelAndView ret = newModelAndView(request, appConfig, viewName);
		ret.addAllObjects(ReflectTool.toMap(t));
		return ret;
	}

	public static ModelAndView newModelAndViewFor404(HttpServletRequest request, HttpServletResponse response,
			AppConfig appConfig) {
		HttpResponseTool.setStatusAsNotFound(response);
		return newModelAndView(request, appConfig, COMMON_404);
	}

	public static ModelAndView newModelAndViewFor404(HttpServletRequest request, HttpServletResponse response,
			AppConfig appConfig, String message) {
		HttpResponseTool.setStatusAsNotFound(response, message);
		return newModelAndView(request, appConfig, COMMON_404);
	}

	public static ModelAndView newModelAndViewAndRedirect(HttpServletRequest request, AppConfig appConfig, String url) {
		ModelAndView ret = new ModelAndView(new RedirectView(url));
		fill(ret, request, appConfig);
		return ret;
	}

}
