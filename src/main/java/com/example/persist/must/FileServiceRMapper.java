package com.example.persist.must;

import java.util.List;

import com.example.domain.FileService;

public interface FileServiceRMapper {

	public List<FileService> selectAll();
	
	public List<FileService> selectAllEnabled();
	
	public FileService selectEnabledByGroupIdAndHost(FileService params);
	
	public FileService selectById(long id);
	
	public FileService selectEnabledById(long id);
	
}
