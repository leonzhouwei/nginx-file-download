package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.common.UuidTool;
import com.example.domain.File;
import com.google.common.collect.Lists;

public class FileDto extends File {
	
	// uuid: for downloading
	public String uuid = UuidTool.newUuid();
	
	public static FileDto toDto(File e) {
		FileDto ret = new FileDto();
		ret.resetBy(e);
		return ret;
	}
	
	public static List<FileDto> toList(Collection<File> list) {
		List<FileDto> ret = Lists.newArrayList();
		for (File e : list) {
			FileDto dto = toDto(e);
			ret.add(dto);
		}
		return ret;
	}

}
