package com.example.common;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;

public final class HttpServletRequestTool {
	
	private HttpServletRequestTool() {
	}
	
	public static String getClientIp(HttpServletRequest request) {
		final String clientIp = request.getHeader("X-Real-IP");
		return clientIp;
	}
	
	public static String getClientIpNullToEmpty(HttpServletRequest request) {
		return Strings.nullToEmpty(getClientIp(request));
	}

}
