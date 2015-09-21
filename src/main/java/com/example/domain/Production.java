package com.example.domain;

public class Production extends Base {
	
	private Long id;
	private String description;
	private Long ret;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getRet() {
		return ret;
	}

	public void setRet(Long ret) {
		this.ret = ret;
	}

}
