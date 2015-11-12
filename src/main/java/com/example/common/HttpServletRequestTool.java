package com.example.common;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;

public final class HttpServletRequestTool {
	
	static final String XFF_SEPARATOR = ",";
	
	private HttpServletRequestTool() {
	}
	
	public static String getClientIp(HttpServletRequest request) {
		String xff = request.getHeader("X-Forwarded-For");
		if (Strings.isNullOrEmpty(xff)) {
			return null;
		}
		String[] split = xff.split(XFF_SEPARATOR);
		if (split.length < 1) {
			return null;
		}
		String ret = split[0].trim();
		return ret;
	}
	
	public static String getClientIpNullToEmpty(HttpServletRequest request) {
		return Strings.nullToEmpty(getClientIp(request));
	}

}
