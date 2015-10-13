package com.example.persist.ssdb.driver.impl;

import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.SSDB;

import com.example.persist.ssdb.driver.SsdbConnPoolConfig;

public final class PooledSsdbProvider {

	private PooledSsdbProvider() {
	}

	public static SSDB newPooledSsdb(SsdbConnPoolConfig config) {
		int maxTotal = config.getMaxActive();
		String host = config.getHost();
		int port = config.getPort();
		int timeout = config.getTimeout();
		String password = config.getAuth();
		boolean testWhileIdle = config.getTestWhileIdle();
		//
		Config anotherConfig = new Config();
		anotherConfig.maxActive = maxTotal;
		anotherConfig.testWhileIdle = testWhileIdle;
		return SSDBs.pool(host, port, timeout, anotherConfig,
				password.getBytes());
	}

}
