package com.example.common;

import com.alibaba.fastjson.JSON;

public class ResponseDto<T> {

	private T content;

	private ServerErrorDto error;

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

	public ServerErrorDto getError() {
		return error;
	}

	public void setError(ServerErrorDto error) {
		this.error = error;
	}
	
}
