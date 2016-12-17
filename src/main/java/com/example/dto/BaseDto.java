package com.example.dto;

import java.util.Date;

import com.example.common.DateTimeTool;

public class BaseDto {
	
	public String createdAt;
	public String updatedAt;
	public Boolean enabled;
	
	public BaseDto() {
	}
	
	public BaseDto(com.example.domain.Base base) {
		Date createdAt = base.getCreatedAt();
		if (createdAt != null) {
			this.createdAt = DateTimeTool.toIso8601(createdAt);
		}
		
		Date updatedAt = base.getUpdatedAt();
		if (updatedAt != null) {
			this.updatedAt = DateTimeTool.toIso8601(updatedAt);
		}
		
		this.enabled = base.getEnabled();
	}

}
