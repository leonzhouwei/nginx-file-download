package com.example.domain;

import com.example.config.AppConfig;

public class DownloadHistory extends Base {

	private Long id;
	private Long taskId;
	private String clientIp;
	private String webServerHost;
	private String appServerUuid;
	private Long appServerThreadId;
	private String appServerThreadName;
	private String requestRoute;
	private String requestParameters;
	
	public void reset() {
		super.reset();
		setWebServerHost(EMPTY_STRING);
		setAppServerUuid(AppConfig.APP_UUID);
		setAppServerThreadId(Thread.currentThread().getId());
		setAppServerThreadName(Thread.currentThread().getName());
		setRequestRoute(EMPTY_STRING);
		setRequestParameters(EMPTY_STRING);
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		if (clientIp == null) {
			clientIp = EMPTY_STRING;
		}
		this.clientIp = clientIp;
	}

	public String getWebServerHost() {
		return webServerHost;
	}

	public void setWebServerHost(String webServerHost) {
		if (webServerHost == null) {
			webServerHost = EMPTY_STRING;
		}
		this.webServerHost = webServerHost;
	}

	public String getAppServerThreadName() {
		return appServerThreadName;
	}

	public void setAppServerThreadName(String appServerThreadName) {
		this.appServerThreadName = appServerThreadName;
	}

	public Long getAppServerThreadId() {
		return appServerThreadId;
	}

	public void setAppServerThreadId(Long appServerThreadId) {
		this.appServerThreadId = appServerThreadId;
	}

	public String getRequestRoute() {
		return requestRoute;
	}

	public void setRequestRoute(String requestRoute) {
		this.requestRoute = requestRoute;
	}

	public String getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(String requestParameters) {
		this.requestParameters = requestParameters;
	}

	public String getAppServerUuid() {
		return appServerUuid;
	}

	public void setAppServerUuid(String appServerUuid) {
		this.appServerUuid = appServerUuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
