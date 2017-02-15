package com.example.web.consts;

public final class RouteDefine {

	// -------------------------------------------------------------------------
	public static final String ROOT = "/";

	public static final String ADMIN = ROOT + "admin";

	public static final String API = ROOT + "api/v1";

	public static final String FILES = ROOT + "files";

	// -------------------------------------------------------------------------
	public static final String BASE_ASSETS = ROOT + "assets";
	public static final String ASSETS_VERSION = BASE_ASSETS + "/v1";

	// -------------------------------------------------------------------------
	public static final String ADMIN_API = API + ADMIN;

	// -------------------------------------------------------------------------
	public static final String I = ROOT + "i";
	
	// -------------------------------------------------------------------------
	public static final String LOGIN = ROOT + "login";
	public static final String LOGOUT = ROOT + "logout";

	private RouteDefine() {
	}

}
