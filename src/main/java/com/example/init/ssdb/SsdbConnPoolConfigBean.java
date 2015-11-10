package com.example.init.ssdb;

import com.example.config.AppConfig;
import com.example.persist.assist.ssdb.driver.impl.SsdbConnPoolConfigImpl;

public class SsdbConnPoolConfigBean extends SsdbConnPoolConfigImpl {
	
	private AppConfig appConfig;
	
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
		setAuth(appConfig.getSsdbAuth());
		setHost(appConfig.getSsdbHost());
		setMaxActive(appConfig.getSsdbMaxActive());
		setPort(appConfig.getSsdbPort());
		setTestWhileIdle(appConfig.isSsdbTestWhileIdle());
		setTimeout(appConfig.getSsdbTimeoutMillis());
	}

}
