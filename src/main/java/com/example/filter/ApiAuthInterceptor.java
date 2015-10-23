package com.example.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpServletResponseUtil;
import com.example.web.RouteDefine;

public class ApiAuthInterceptor implements HandlerInterceptor {

	public static final String PREFIX = "example.com/";
	public static final String SEESION_ID = PREFIX + "sessionId";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String route = request.getRequestURI();
		if (!route.startsWith(RouteDefine.API)) {
			return true;
		}
		Long userId = LoginInterceptor.getUserId(request);
		if (userId != null) {
			return true;
		}
		HttpServletResponseUtil.setStatusAsUnauthorized(response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// no operations
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// no operations
	}

}
