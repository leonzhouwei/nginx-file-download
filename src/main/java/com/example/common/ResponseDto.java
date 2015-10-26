package com.example.common;

public class ResponseDto<T> {

	private T content;

	private ServerErrorDto error;

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
