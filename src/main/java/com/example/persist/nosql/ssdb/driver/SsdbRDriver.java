package com.example.persist.nosql.ssdb.driver;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SsdbRDriver {

	// string ------------------------------------------------------------------
	public String get(String key);
	
	// queue -------------------------------------------------------------------
	/**
	 * 
	 * @param key
	 * @param index 
	 * @return
	 */
	public String qget(String key, int index);
	
	// hash --------------------------------------------------------------------
	public Map<String, String> hgetall(String key);
	
	public List<Map<String, String>> batchHgetall(Collection<String> keys);

}
