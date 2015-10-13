package com.example.persist.ssdb.driver;

public interface SsdbRDriver {

	// string --------------------------------------------------
	public String get(String key);
	
	// queue -------------------------------------------------------------------
	/**
	 * 
	 * @param key
	 * @param index 
	 * @return
	 */
	public String qget(String key, int index);

}
