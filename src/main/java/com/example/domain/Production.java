package com.example.domain;

import org.apache.commons.lang3.StringUtils;

public class Production extends Base {
	
	private Long id;
	private String name = StringUtils.EMPTY;
	private String description = StringUtils.EMPTY;
	private String dir = StringUtils.EMPTY;
	
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
		if (description == null) {
			description = StringUtils.EMPTY;
		}
		this.description = description;
	}
	
	public void resetDescription(String description) {
		setDescription(description);
		resetUpdatedAt();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			name = StringUtils.EMPTY;
		}
		this.name = name;
	}
	
	public void resetName(String name) {
		setName(name);
		resetUpdatedAt();
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		if (dir == null) {
			dir = StringUtils.EMPTY;
		}
		this.dir = dir;
	}

}
