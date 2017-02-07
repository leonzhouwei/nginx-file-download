package com.example.domain;

public class FileServiceGroup extends Base {

	private Long id;
	private String name;
	
	@Override
	public String toString() {
		return super.toString() + " FileServiceGroup [id=" + id + ", name=" + name + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
