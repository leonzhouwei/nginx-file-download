package com.example.domain;

import org.apache.commons.lang3.StringUtils;

public class File extends Base {

	private Long id;
	private String dir;
	private String name;
	private Long size;
	private String md;
	private Production production = new Production();
	private FileServiceGroup fileServiceGroup = new FileServiceGroup();

	public void resetBy(File another) {
		super.resetBy(another);
		setId(another.getId());
		setDir(another.getDir());
		setName(another.getName());
		setSize(another.getSize());
		setProduction(another.getProduction());
		setFileServiceGroup(another.getFileServiceGroup());
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public FileServiceGroup getFileServiceGroup() {
		return fileServiceGroup;
	}

	public void setFileServiceGroup(FileServiceGroup group) {
		this.fileServiceGroup = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		if (dir == null) {
			this.dir = StringUtils.EMPTY;
			return;
		}
		
		this.dir = dir.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

}
