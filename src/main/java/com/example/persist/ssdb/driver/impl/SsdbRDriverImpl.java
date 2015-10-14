package com.example.persist.ssdb.driver.impl;

import org.nutz.ssdb4j.spi.Response;

import com.example.persist.ssdb.driver.SsdbRDriver;

public class SsdbRDriverImpl extends SsdbBaseDriver implements SsdbRDriver {
	
	public SsdbRDriverImpl() {
	}

	// read string -------------------------------------------------------------
	@Override
	public final String get(String key) {
		Response response = ssdb.get(key);
		String ret = extractString(response);
		return ret;
	}

	// queue -------------------------------------------------------------------
	public String qget(String key, int index) {
		Response response = ssdb.qget(key, index);
		String ret = extractString(response);
		return ret;
	}
	
}
