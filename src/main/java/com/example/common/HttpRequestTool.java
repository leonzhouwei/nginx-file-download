package com.example.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Strings;

public final class HttpRequestTool {

	public static final String ENABLED = "enabled";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String MD = "md";
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String SIZE = "size";

	private HttpRequestTool() {
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

	public static String extractDescription(HttpServletRequest request) {
		return request.getParameter(DESCRIPTION);
	}

	public static String extractMd(HttpServletRequest request) {
		return request.getParameter(MD);
	}
	
	public static String extractName(HttpServletRequest request) {
		return request.getParameter(NAME);
	}

	public static Boolean extractBoolean(HttpServletRequest request,
			String paramName) {
		return Boolean.valueOf(request.getParameter(paramName));
	}

	public static Boolean extractEnabled(HttpServletRequest request) {
		return extractBoolean(request, ENABLED);
	}

	public static Boolean extractEnabled(HttpServletRequest request,
			boolean defaultValue) {
		Boolean ret = extractBoolean(request, ENABLED);
		if (ret == null) {
			return defaultValue;
		}
		return ret;
	}

	public static Long extractLong(HttpServletRequest request, String paramName) {
		return Long.valueOf(request.getParameter(paramName));
	}

	public static Long extractId(HttpServletRequest request) {
		return Long.valueOf(request.getParameter(ID));
	}

	public static Long extractPriceFromYuanToFen(HttpServletRequest request,
			String priceParam) {
		String priceStr = request.getParameter(priceParam);
		if (Strings.isNullOrEmpty(priceStr)) {
			return null;
		}
		return MoneyTool.yuanToFen(priceStr);
	}

	public static Long extractPriceFromYuanToFen(HttpServletRequest request) {
		String priceStr = request.getParameter(PRICE);
		if (Strings.isNullOrEmpty(priceStr)) {
			return null;
		}
		return MoneyTool.yuanToFen(priceStr);
	}

	public static Long extractSize(HttpServletRequest request) {
		String sizeStr = request.getParameter(SIZE);
		if (Strings.isNullOrEmpty(sizeStr)) {
			return null;
		}
		return Long.valueOf(sizeStr);
	}

}
