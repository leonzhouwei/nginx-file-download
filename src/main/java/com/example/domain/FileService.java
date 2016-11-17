package com.example.domain;

public class FileService extends Base {
	
	private Long id;
	private String host;
	private Long groupId;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	} 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
