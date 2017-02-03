package com.example.webapi;

public final class RouteDefine {

	// -------------------------------------------------------------------------
	public static final String ROOT = "/";

	public static final String ADMIN = ROOT + "admin";

	public static final String API = ROOT + "api/v1";

	public static final String FILES = ROOT + "files";

	// -------------------------------------------------------------------------
	public static final String BASE_ASSETS = ROOT + "assets";
	public static final String ASSETS_VERSION = BASE_ASSETS + "/v1";
	public static final String ASSETS_VERSION_IMAGES = ASSETS_VERSION + "/images";
	public static final String ASSETS_VERSION_CSS = ASSETS_VERSION + "/css";
	public static final String ASSETS_VERSION_JAVASCRIPT = ASSETS_VERSION + "/js";

	// -------------------------------------------------------------------------
	public static final String API_ADMIN = API + ADMIN;
	public static final String API_ADMIN_FILES = API_ADMIN + "/files";
	public static final String API_ADMIN_FILE_SERVICES = API_ADMIN + "/file-services";
	public static final String API_ADMIN_FILE_SERVICE_GROUPS = API_ADMIN + "/fsgroups";
	public static final String API_ADMIN_PRODUCTIONS = API_ADMIN + "/productions";
	public static final String API_ADMIN_ACCOUNTS = API_ADMIN + "/accounts";

	// -------------------------------------------------------------------------
	public static final String ADMIN_ACCOUNTS = ADMIN + "/accounts";
	public static final String ADMIN_ACCOUNTS_NEW = ADMIN_ACCOUNTS + "/new";
	public static final String ADMIN_ACCOUNTS_EDIT_PASSWORD = ADMIN_ACCOUNTS + "/edit-pswd";
	
	public static final String ADMIN_DOWNLOAD_TASKS = ADMIN + "/dld-tasks";
	
	public static final String ADMIN_FILE_SERVICE_GROUPS = ADMIN + "/fsgroups";
	public static final String ADMIN_FILE_SERVICE_GROUPS_NEW = ADMIN_FILE_SERVICE_GROUPS + "/new";
	
	public static final String ADMIN_FILE_SERVICES = ADMIN + "/file-services";
	public static final String ADMIN_FILE_SERVICES_NEW = ADMIN_FILE_SERVICES + "/new";
	
	public static final String ADMIN_FILES = ADMIN + "/files";
	public static final String ADMIN_FILES_NEW = ADMIN_FILES + "/new";
	public static final String ADMIN_FILES_EDIT = ADMIN_FILES + "/edit";
	public static final String ADMIN_FILES_DISABLE = ADMIN_FILES + "/disable";
	public static final String ADMIN_FILES_ENABLE = ADMIN_FILES + "/enable";
	
	public static final String ADMIN_PRODUCTIONS = ADMIN + "/productions";
	public static final String ADMIN_PRODUCTIONS_DISABLE = ADMIN_PRODUCTIONS + "/disable";
	public static final String ADMIN_PRODUCTIONS_EDIT = ADMIN_PRODUCTIONS + "/edit";
	public static final String ADMIN_PRODUCTIONS_ENABLE = ADMIN_PRODUCTIONS + "/enable";
	public static final String ADMIN_PRODUCTIONS_NEW = ADMIN_PRODUCTIONS + "/new";

	// -------------------------------------------------------------------------
	public static final String I = ROOT + "i";
	public static final String I_DOWNLOAD_TASKS = I + "/dld-tasks";

	public static final String LOGIN = ROOT + "login";
	public static final String LOGOUT = ROOT + "logout";

	public static final String APP = ROOT + "app";
	public static final String APP_UUID = APP + "/uuid";
	public static final String APP_VERSION = APP + "/version";
	
	private RouteDefine() {
	}

}
