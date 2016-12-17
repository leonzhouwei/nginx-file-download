package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.domain.File;
import com.google.common.collect.Lists;

public class FileDto extends Base {

	public Long id;
	public String dir;
	public String name;
	public Long size;
	public ProductionDto production;
	public String md;
	public FileDto fileServiceGroup;
	
	public static List<FileDto> toList(Collection<File> c) {
		List<FileDto> ret = Lists.newArrayList();
		for (File e : c) {
			FileDto dto = new FileDto(e);
			ret.add(dto);
		}
		return ret;
	}
	
	public FileDto() {
		super();
	}
	
	public FileDto(File e) {
		super(e);
		this.id = e.getId();
		this.dir = e.getDir();
		this.name = e.getName();
		this.size = e.getSize();
		this.production = new ProductionDto();
		this.production.id = e.getProductionId();
		this.md = e.getMd();
		this.fileServiceGroup = new FileDto();
		this.fileServiceGroup.id = e.getFileServiceGroupId();
	}

}
