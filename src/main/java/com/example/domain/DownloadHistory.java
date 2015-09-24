package com.example.domain;

public class DownloadHistory extends Base {

	private Long taskId;
	private String clientIp;
	private String webServerHost;
	private Long appServerThreadId;
	private String appServerThreadName;
	
	public void reset() {
		super.reset();
		setWebServerHost("");
		setAppServerThreadId(Thread.currentThread().getId());
		setAppServerThreadName(Thread.currentThread().getName());
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
		this.clientIp = clientIp;
	}

	public String getWebServerHost() {
		return webServerHost;
	}

	public void setWebServerHost(String webServerHost) {
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

}
