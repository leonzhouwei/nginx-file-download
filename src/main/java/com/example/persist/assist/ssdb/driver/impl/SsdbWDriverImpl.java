package com.example.persist.assist.ssdb.driver.impl;

import java.util.List;
import java.util.Map;

import org.nutz.ssdb4j.spi.Response;

import com.example.persist.assistl.ssdb.driver.SsdbWDriver;

public class SsdbWDriverImpl extends SsdbBaseDriver implements SsdbWDriver {

	static final int MAX = 5000;

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

	// write hash --------------------------------------------------------------
	@Override
	public void hset(String key, Long field, String value) {
		hset(key, field.toString(), value);
	}

	@Override
	public void hset(String key, String field, String value) {
		ssdb.hset(key, field, value);
	}

	@Override
	public void multiHset(String key, Map<String, String> map) {
		List<Map<String, String>> list = SplitTool.split(map, MAX);
		for (Map<String, String> e : list) {
			ssdb.multi_hset(key, e);
		}
	}

}
