package com.example.domain;

import org.apache.commons.lang3.StringUtils;

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
		setWebServerHost(StringUtils.EMPTY);
		setAppServerUuid(AppConfig.APP_UUID);
		setAppServerThreadId(Thread.currentThread().getId());
		setAppServerThreadName(Thread.currentThread().getName());
		setRequestRoute(StringUtils.EMPTY);
		setRequestParameters(StringUtils.EMPTY);
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
			this.clientIp = StringUtils.EMPTY;
			return;
		}
		
		this.clientIp = clientIp;
	}

	public String getWebServerHost() {
		return webServerHost;
	}

	public void setWebServerHost(String webServerHost) {
		if (webServerHost == null) {
			this.webServerHost = StringUtils.EMPTY;
			return;
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
