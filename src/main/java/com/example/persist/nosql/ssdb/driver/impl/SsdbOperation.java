package com.example.persist.nosql.ssdb.driver.impl;

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
