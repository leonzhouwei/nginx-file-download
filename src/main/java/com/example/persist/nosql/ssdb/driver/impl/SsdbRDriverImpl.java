package com.example.persist.nosql.ssdb.driver.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;

import com.example.persist.nosql.ssdb.driver.SsdbRDriver;
import com.google.common.collect.Lists;

public class SsdbRDriverImpl extends SsdbBaseDriver implements SsdbRDriver {
	
	public SsdbRDriverImpl() {
	}

	// string -------------------------------------------------------------
	@Override
	public final String get(String key) {
		Response response = ssdb.get(key);
		String ret = extractString(response);
		return ret;
	}

	// queue -------------------------------------------------------------------
	@Override
	public String qget(String key, int index) {
		Response response = ssdb.qget(key, index);
		String ret = extractString(response);
		return ret;
	}
	
	// hash --------------------------------------------------------------------
	@Override
	public List<Map<String, String>> batchHgetall(Collection<String> keys) {
		List<Map<String, String>> ret = Lists.newArrayList();
		SSDB batch = ssdb.batch();
		for (String e : keys) {
			batch.hgetall(e);
		}
		List<Response> resps = batch.exec();
		for (Response e : resps) {
			Map<String, String> map = extractStringMap(e);
			ret.add(map);
		}
		return ret;
	}

	@Override
	public Map<String, String> hgetall(String key) {
		Response r = ssdb.hgetall(key);
		return extractStringMap(r);
	}
	
}
