package com.example.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class ResponseDto<T> {

	private String innerVersion;

	private T content;

	private ServerError error;

	private Map<String, Object> extra = Maps.newHashMap();

	public static <T> String toJson(ResponseDto<T> r) {
		String json = JSON.toJSONString(r);
		return json;
	}

	public String toJson() {
		return toJson(this);
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public ServerError getError() {
		return error;
	}

	public void setError(ServerError error) {
		this.error = error;
	}
	
	public void addExtra(String key, Object o) {
		extra.put(key, o);
	}

	public Map<String, Object> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}

	public String getInnerVersion() {
		return innerVersion;
	}

	public void setInnerVersion(String innerVersion) {
		this.innerVersion = innerVersion;
	}

}
