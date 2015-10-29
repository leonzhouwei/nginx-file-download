package com.example.persist.nosql.ssdb.driver;

import java.util.Map;

public interface SsdbWDriver {

	// string ------------------------------------------------------------------
	public Long incrBy(String key, int delta);

	public Long incrByOne(String key);

	public void set(String key, String value);
	
	// queue -------------------------------------------------------------------
	public void qpush(String key, String e);
	
	public void qpush(String key, long e);
	
	public String qpop(String key);

	// hash --------------------------------------------------------------------
	void hset(String key, Long field, String value);

	void hset(String key, String field, String value);

	void multiHset(String key, Map<String, String> map);

}
