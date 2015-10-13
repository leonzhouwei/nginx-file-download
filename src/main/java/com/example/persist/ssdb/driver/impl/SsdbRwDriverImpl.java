package com.example.persist.ssdb.driver.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import org.nutz.ssdb4j.spi.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.persist.ssdb.driver.SsdbRwDriver;
import com.example.util.JsonTool;

public class SsdbRwDriverImpl extends SsdbRDriverImpl implements SsdbRwDriver {

	static final String SPACE_STRING = " ";
	
	private static final AtomicBoolean verbose = new AtomicBoolean(false);

	private static final Logger logger = LoggerFactory
			.getLogger(SsdbRwDriverImpl.class);

	public static AtomicBoolean getVerbose() {
		return verbose;
	}

	public static boolean setVerbose(boolean b) {
		boolean ret = verbose.get();
		verbose.set(b);
		return ret;
	}

	// write string ------------------------------------------------------------
	@Override
	public Long incrByOne(String key) {
		return incrBy(key, 1);
	}

	@Override
	public Long incrBy(String key, int delta) {
		logStart(SsdbOperation.incr, key, new FieldValuePair<Integer>(null,
				delta));
		if (delta < 0) {
			return null;
		}
		Response response = getSsdb().incr(key, delta);
		Long ret = extractLong(response);
		logger.debug("result: " + ret);
		return ret;
	}

	@Override
	public void set(String key, String value) {
		logStart(SsdbOperation.set, key, null, value);
		getSsdb().set(key, value);
	}

	// queue -------------------------------------------------------------------
	@Override
	public void qpush(String key, String e) {
		logStart(SsdbOperation.qpush, key, e, null);
		getSsdb().qpush(key, e);
	}

	@Override
	public void qpush(String key, long e) {
		String value = Long.toString(e);
		qpush(key, value);
	}

	@Override
	public String qpop(String key) {
		logStart(SsdbOperation.qpop, key, null, null);
		Response response = getSsdb().qpop(key);
		return extractString(response);
	}

	/**************************************************************************/

	private <T> void logStart(SsdbOperation operation, String key,
			String field, T value) {
		StringBuilder sb = new StringBuilder();
		sb.append(operation);
		sb.append(SPACE_STRING);
		sb.append(key);
		if (field != null) {
			sb.append(SPACE_STRING);
			sb.append(field);
		}
		if (value != null && verbose.get()) {
			sb.append(SPACE_STRING);
			sb.append(JsonTool.toJson(value));
		}
		logger.debug(sb.toString());
	}

	private <T> void logStart(SsdbOperation operation, String key,
			FieldValuePair<T> fvp) {
		if (fvp == null) {
			logStart(operation, key, null, null);
			return;
		}
		logStart(operation, key, fvp.getField(), fvp.getValue());
	}

}
