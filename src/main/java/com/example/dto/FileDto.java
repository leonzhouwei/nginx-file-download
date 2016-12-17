package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.domain.File;
import com.example.domain.FileServiceGroup;
import com.example.domain.Production;
import com.google.common.collect.Lists;

public class FileDto extends BaseDto {

	public Long id;
	public String dir;
	public String name;
	public Long size;
	public Production production;
	public String md;
	public FileServiceGroup fileServiceGroup;

	public static List<FileDto> toList(Collection<File> c) {
		List<FileDto> ret = Lists.newArrayList();
		if (c == null) {
			return ret;
		}
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
		this.production = new Production();
		this.production.setId(e.getProductionId());
		this.md = e.getMd();
		this.fileServiceGroup = new FileServiceGroup();
		this.fileServiceGroup.setId(e.getFileServiceGroupId());
	}

}
