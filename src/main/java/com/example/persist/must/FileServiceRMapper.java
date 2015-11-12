package com.example.persist.must;

import java.util.List;

import com.example.domain.FileService;

public interface FileServiceRMapper {

	public List<FileService> selectAll();
	
	public FileService selectByGroupIdAndHost(FileService params);
	
	public FileService selectByGroupIdAndHostAndPort(FileService params);
	
}
