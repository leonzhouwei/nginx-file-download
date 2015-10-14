package com.example.persist.ssdb.driver.impl;

import java.io.IOException;
import java.util.Map;

import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;

import com.example.persist.ssdb.driver.SsdbConnPoolConfig;
import com.google.common.collect.Maps;

public abstract class SsdbBaseDriver {
	
	static final String SPACE_STRING = " ";
	
	SsdbConnPoolConfig config;
	SSDB ssdb;

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

	public final SsdbConnPoolConfig getConfig() {
		return config;
	}

	public final void setConfig(SsdbConnPoolConfig config) {
		this.config = config;
	}

	public final SSDB getSsdb() {
		return ssdb;
	}

	public final void setSsdb(SSDB ssdb) {
		this.ssdb = ssdb;
	}
	
	public final void init(SsdbConnPoolConfig config) {
		setConfig(config);
		ssdb = PooledSsdbProvider.newPooledSsdb(config);
	}
	
	public final void closeSsdb() throws IOException {
		if (ssdb == null) {
			return;
		}
		ssdb.close();
		ssdb = null;
	}
	
}
