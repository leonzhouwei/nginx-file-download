package com.example.domain;

public class Production extends Base {
	
	private Long id;
	private String name;
	private String description;
	private String dir;
	
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
			this.description = EMPTY_STRING;
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
		this.dir = dir;
	}

}
