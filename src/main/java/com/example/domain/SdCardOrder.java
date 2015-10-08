package com.example.domain;

public class SdCardOrder extends Base {
	
	static final int STATUS_WAITING = 1;

	private Long id;
	private Long fileId;
	private Integer status;
	private String username;
	private String userAddr;
	private String userZipCode;
	private String userMobile;
	private String userEmail;
	
	public void reset() {
		super.reset();
		setStatus(STATUS_WAITING);
		setUserMobile(EMPTY_STRING);
		setUserEmail(EMPTY_STRING);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserZipCode() {
		return userZipCode;
	}

	public void setUserZipCode(String userZipCode) {
		this.userZipCode = userZipCode;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
