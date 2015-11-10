package com.example.persist.assist.ssdb.driver.impl;

enum SsdbOperation {
	// string ------------------------------------------------------------------
	get, 
	set, 
	
	// hash --------------------------------------------------------------------
	hexists,
	hget, 
	hgetall,
	hkeys,
	hset, 
	hsize, 
	incr, 
	multi_hget, 
	multi_hset, 
	
	// queue -------------------------------------------------------------------
	qget,
	qpop,
	qpush;
	
}
