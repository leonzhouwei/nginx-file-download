package com.example.web;

public final class RouteDefine {

	private RouteDefine() {
	}
	
	public static final String ROOT = "/";
	
	public static final String API = ROOT + "api";
	
	public static final String SYSTEM = ROOT + "system";
	public static final String SYSTEM_UUID = SYSTEM + "/uuid";
	public static final String SYSTEM_VERSION = SYSTEM + "/version";
	
}
