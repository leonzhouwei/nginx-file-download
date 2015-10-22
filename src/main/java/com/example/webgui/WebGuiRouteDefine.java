package com.example.webgui;

public final class WebGuiRouteDefine {

	private WebGuiRouteDefine() {
	}

	public static final String ROOT = "/";

	public static final String FILES = ROOT + "files";

	public static final String ADMIN = ROOT + "admin";
	public static final String ADMIN_DOWNLOAD_TASKS = ADMIN + "/dld-tasks";
	public static final String ADMIN_SD_CARD_ORDERS = ADMIN + "/sd-card-orders";

	public static final String LOGIN = ROOT + "login";
	public static final String LOGOUT = ROOT + "logout";

}
