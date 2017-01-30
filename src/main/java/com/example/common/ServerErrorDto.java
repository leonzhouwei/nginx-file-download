package com.example.common;

public class ServerErrorDto {

	private String id;
	private String message;
	private String url;
	
	public ServerErrorDto(String message) {
		this(null, message, null);
	}
	
	public ServerErrorDto(String id, String message, String url) {
		this.id = id;
		this.message = message;
		this.url = url;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
