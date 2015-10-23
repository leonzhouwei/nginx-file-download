package com.example.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.web.RouteDefine;
import com.example.webgui.WebGuiRouteDefine;
import com.google.common.collect.Sets;

public class LoginInterceptor implements HandlerInterceptor {

	public static final String PREFIX = "example.com/";
	public static final String SEESION_ID = PREFIX + "sessionId";

	private static final Set<String> unrestrictedRoutePatterns = Sets
			.newHashSet();
	static {
		unrestrictedRoutePatterns.add("/");
		unrestrictedRoutePatterns.add(WebGuiRouteDefine.LOGIN + ".*");
		unrestrictedRoutePatterns.add(RouteDefine.API + "/.*");
		unrestrictedRoutePatterns.add(RouteDefine.ASSETS + "/.*");
	}

	public static void redirectToLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(WebGuiRouteDefine.LOGIN);
	}

	public static void setSessionId(HttpServletRequest request, Long userId) {
		request.getSession().setAttribute(SEESION_ID, userId);
	}

	public static Long getSessionId(HttpServletRequest request) {
		Long ret = (Long) request.getSession().getAttribute(SEESION_ID);
		return ret;
	}
	
	public static boolean sessionIdExist(HttpServletRequest request) {
		Long sessionId = getSessionId(request);
		return sessionId != null;
	}
	
	public static void removeSessionId(HttpServletRequest request) {
		if (!sessionIdExist(request)) {
			return;
		}
		request.getSession().removeAttribute(SEESION_ID);
	}
	
	public static Long getUserId(HttpServletRequest request) {
		return getSessionId(request);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		for (String e : unrestrictedRoutePatterns) {
			if (uri.matches(e)) {
				return true;
			}
		}
		Long sessionId = getSessionId(request);
		if (sessionId != null) {
			return true;
		} 
		redirectToLogin(request, response);
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
