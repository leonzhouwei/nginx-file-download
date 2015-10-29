package com.example.init.ssdb;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.persist.nosql.ssdb.driver.impl.SsdbWDriverImpl;

@Component
public class SsdbWDriverBean extends SsdbWDriverImpl implements
		InitializingBean, DisposableBean {

	private static final Logger logger = LoggerFactory
			.getLogger(SsdbWDriverBean.class);

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
