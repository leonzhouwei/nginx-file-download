package com.example.persist.ssdb.driver.impl;

import java.io.IOException;
import java.util.Map;

import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.persist.ssdb.driver.SsdbConnPoolConfig;
import com.example.persist.ssdb.driver.SsdbRDriver;
import com.google.common.collect.Maps;

public class SsdbRDriverImpl implements SsdbRDriver {

	private static final Logger logger = LoggerFactory
			.getLogger(SsdbRDriverImpl.class);

	private SsdbConnPoolConfig config;
	private SSDB ssdb;

	public static final boolean extractBoolean(Response response) {
		if (response.notFound()) {
			return false;
		}
		return response.asInt() == 1;
	}

	public static final Long extractLong(Response response, Long l) {
		if (response.notFound()) {
			return l;
		}
		return response.asLong();
	}

	public static final Long extractLong(Response response) {
		return extractLong(response, null);
	}

	public static final String extractString(Response response) {
		if (response.notFound()) {
			return null;
		}
		return response.asString();
	}

	public static final Map<String, String> extractStringMap(Response response) {
		if (response.notFound()) {
			return Maps.newHashMap();
		}
		return response.mapString();
	}

	public SsdbRDriverImpl() {
	}

	// read string -------------------------------------------------------------
	@Override
	public final String get(String key) {
		logStart(SsdbOperation.get, key, null, null);
		Response response = ssdb.get(key);
		String ret = extractString(response);
		logger.debug("result: " + ret);
		return ret;
	}

	// queue -------------------------------------------------------------------
	public String qget(String key, int index) {
		logStart(SsdbOperation.qget, key, null, null);
		Response response = ssdb.qget(key, index);
		String ret = extractString(response);
		logger.debug("result: " + ret);
		return ret;
	}

	// --------------------------------------------------------------------------

	private <T> void logStart(SsdbOperation operation, String key,
			String field, T value) {
		logger.debug("operation: " + operation);
		logger.debug("key: " + key);
		if (field != null) {
			logger.debug("field: " + field);
		}
	}

	public SsdbConnPoolConfig getConfig() {
		return config;
	}

	public void setConfig(SsdbConnPoolConfig config) {
		this.config = config;
	}
	
	public final void init(SsdbConnPoolConfig config) {
		setConfig(config);
		ssdb = PooledSsdbProvider.newPooledSsdb(config);
	}

	public SSDB getSsdb() {
		return ssdb;
	}
	
	public void closeSsdb() throws IOException {
		if (ssdb == null) {
			return;
		}
		ssdb.close();
		ssdb = null;
	}
	
}
