package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.domain.FileServiceGroup;
import com.google.common.collect.Lists;

public class FileServiceGroupDto extends Base {
	
	public Long id;
	public String name;
	
	public static List<FileServiceGroupDto> toList(Collection<FileServiceGroup> c) {
		List<FileServiceGroupDto> ret = Lists.newArrayList();
		for (FileServiceGroup e : c) {
			FileServiceGroupDto dto = new FileServiceGroupDto(e);
			ret.add(dto);
		}
		return ret;
	}
	
	public FileServiceGroupDto() {
		super();
	}
	
	public FileServiceGroupDto(FileServiceGroup e) {
		super(e);
		this.id = e.getId();
		this.name = e.getName();
	}
	
}
