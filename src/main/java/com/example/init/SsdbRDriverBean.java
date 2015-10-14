package com.example.init;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.persist.ssdb.driver.impl.SsdbRDriverImpl;

public class SsdbRDriverBean extends SsdbRDriverImpl implements
		InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory
			.getLogger(SsdbRDriverBean.class);

	@Autowired
	private SsdbConnPoolConfigBean ssdbConnPoolConfig;

	@Override
	public void afterPropertiesSet() throws Exception {
		super.init(ssdbConnPoolConfig);
	}

	@Override
	public void destroy() {
		try {
			super.closeSsdb();
		} catch (IOException e) {
			logger.warn("", e);
		}
	}

}
