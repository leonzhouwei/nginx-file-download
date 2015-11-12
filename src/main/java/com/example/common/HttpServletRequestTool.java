package com.example.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;

public final class HttpServletRequestTool {

	private HttpServletRequestTool() {
	}

	public static final String toEntireIp(String ipAddr)
			throws UnknownHostException {
		InetAddress inetAddr = InetAddress.getByName(ipAddr);
		ipAddr = inetAddr.getHostAddress();
		return ipAddr;
	}

	public static String getClientIp(HttpServletRequest request)
			throws UnknownHostException {
		String xff = request.getHeader(HttpDefine.XFF);
		if (Strings.isNullOrEmpty(xff)) {
			return toEntireIp(request.getRemoteAddr());
		}
		String[] split = xff.split(HttpDefine.XFF_SEPARATOR);
		if (split.length < 1) {
			return toEntireIp(request.getRemoteAddr());
		}
		String ret = split[0].trim();
		return ret;
	}

	public static String getClientIpNullToEmpty(HttpServletRequest request)
			throws UnknownHostException {
		return Strings.nullToEmpty(getClientIp(request));
	}

}
