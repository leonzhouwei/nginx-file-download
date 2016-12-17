package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.domain.FileService;
import com.example.domain.FileServiceGroup;
import com.google.common.collect.Lists;

public class FileServiceDto extends BaseDto {

	public Long id;
	public String host;
	public FileServiceGroup group;
	public String name;

	public static List<FileServiceDto> toList(Collection<FileService> c) {
		List<FileServiceDto> ret = Lists.newArrayList();
		if (c == null) {
			return ret;
		}
		for (FileService e : c) {
			FileServiceDto dto = new FileServiceDto(e);
			ret.add(dto);
		}
		return ret;
	}

	public FileServiceDto() {
		super();
	}

	public FileServiceDto(FileService e) {
		super(e);
		this.id = e.getId();
		this.host = e.getHost();
		this.group = new FileServiceGroup();
		this.group.setId(e.getGroupId());
		this.name = e.getName();
	}

}
