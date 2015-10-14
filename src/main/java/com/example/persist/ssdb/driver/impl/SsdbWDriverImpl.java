package com.example.persist.ssdb.driver.impl;

import org.nutz.ssdb4j.spi.Response;

import com.example.persist.ssdb.driver.SsdbWDriver;

public class SsdbWDriverImpl extends SsdbBaseDriver implements SsdbWDriver {

	// write string ------------------------------------------------------------
	@Override
	public Long incrByOne(String key) {
		return incrBy(key, 1);
	}

	@Override
	public Long incrBy(String key, int delta) {
		if (delta < 0) {
			return null;
		}
		Response response = ssdb.incr(key, delta);
		Long ret = extractLong(response);
		return ret;
	}

	@Override
	public void set(String key, String value) {
		ssdb.set(key, value);
	}

	// queue -------------------------------------------------------------------
	@Override
	public void qpush(String key, String e) {
		ssdb.qpush(key, e);
	}

	@Override
	public void qpush(String key, long e) {
		String value = Long.toString(e);
		qpush(key, value);
	}

	@Override
	public String qpop(String key) {
		Response response = ssdb.qpop(key);
		return extractString(response);
	}

}
