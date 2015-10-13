package com.example.persist.ssdb.driver;

public interface SsdbRwDriver extends SsdbRDriver {

	// string ------------------------------------------------------------------

	public Long incrBy(String key, int delta);

	public Long incrByOne(String key);

	public void set(String key, String value);
	
	// queue -------------------------------------------------------------------
	public void qpush(String key, String e);
	
	public void qpush(String key, long e);
	
	public String qpop(String key);

}
