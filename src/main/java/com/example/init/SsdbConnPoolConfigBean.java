package com.example.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.config.AppConfig;
import com.example.persist.nosql.ssdb.driver.impl.SsdbConnPoolConfigImpl;

@Component
public class SsdbConnPoolConfigBean extends SsdbConnPoolConfigImpl
		implements InitializingBean {

	@Autowired
	private AppConfig appConfig;

	@Override
	public void afterPropertiesSet() throws Exception {
		super.setAuth(appConfig.getSsdbAuth());
		super.setHost(appConfig.getSsdbHost());
		super.setMaxActive(appConfig.getSsdbMaxActive());
		super.setPort(appConfig.getSsdbPort());
		super.setTestWhileIdle(appConfig.isSsdbTestWhileIdle());
		super.setTimeout(appConfig.getSsdbTimeoutMillis());
	}

}
