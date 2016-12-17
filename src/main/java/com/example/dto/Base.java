package com.example.dto;

import com.example.common.DateTimeTool;

public class Base {
	
	public String createdAt;
	public String updatedAt;
	public Boolean enabled;
	
	public Base() {
	}
	
	public Base(com.example.domain.Base base) {
		this.createdAt = DateTimeTool.toIso8601(base.getCreatedAt());
		this.updatedAt = DateTimeTool.toIso8601(base.getUpdatedAt());
		this.enabled = base.getEnabled();
	}

}
