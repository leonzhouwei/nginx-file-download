package com.example.domain;

public class Production extends Base {
	
	private Long id;
	private String name = EMPTY_STRING;
	private String description = EMPTY_STRING;
	private String dir = EMPTY_STRING;
	
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
			description = EMPTY_STRING;
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
			name = EMPTY_STRING;
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
			dir = EMPTY_STRING;
		}
		this.dir = dir;
	}

}
