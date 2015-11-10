package com.example.persist.assist.ssdb.driver.impl;

import com.example.persist.assistl.ssdb.driver.SsdbConnPoolConfig;

public class SsdbConnPoolConfigImpl implements SsdbConnPoolConfig {
	
	private Integer timeout;
	private Integer maxActive;
	private Boolean testWhileIdle;
	private String host;
	private int port;
	private String auth;

	public final Integer getTimeout() {
		return timeout;
	}

	public final void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public final Integer getMaxActive() {
		return maxActive;
	}

	public final void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public final Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public final void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public final String getHost() {
		return host;
	}

	public final void setHost(String host) {
		this.host = host;
	}

	public final int getPort() {
		return port;
	}

	public final void setPort(int port) {
		this.port = port;
	}

	public final String getAuth() {
		return auth;
	}

	public final void setAuth(String auth) {
		this.auth = auth;
	}
	
}
