package com.example.domain;

import org.apache.commons.lang3.StringUtils;

public class Production extends Base {

	private Long id;
	private String name = StringUtils.EMPTY;
	private String description = StringUtils.EMPTY;
	private String dir = StringUtils.EMPTY;
	
	@Override
	public String toString() {
		return super.toString() + " Production [id=" + id + ", name=" + name + ", description=" + description + ", dir="
				+ dir + "]";
	}

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
			this.description = StringUtils.EMPTY;
			return;
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
			this.name = StringUtils.EMPTY;
			return;
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
			this.dir = StringUtils.EMPTY;
			return;
		}

		this.dir = dir;
	}

}
