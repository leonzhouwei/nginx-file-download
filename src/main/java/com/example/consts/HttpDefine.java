package com.example.consts;

public final class HttpDefine {

	public static final String CONTENT_DISPOSITION = "Content-Disposition";

	public static final String CONTENT_TYPE_KEY = "Content-Type";
	public static final String CONTENT_TYPE_VALUE_APP_JSON = "application/json";
	public static final String CONTENT_TYPE_VALUE_APP_OCTETSTREAM = "application/octet-stream";
	public static final String CONTENT_TYPE_VALUE_APPJSON_AND_UTF8 = "application/json;charset=utf-8";
	public static final String CONTENT_TYPE_VALUE_APP_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
	
	public static final String USER_AGENT_KEY = "User-Agent";
	public static final String USER_AGENT_VALUE = "Mozilla/5.0";
	
	public static final String XFF = "X-Forwarded-For";
	public static final String XFF_SEPARATOR = ",";
	
	private HttpDefine() {
	}

}
