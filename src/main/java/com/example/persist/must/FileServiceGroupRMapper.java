package com.example.persist.must;

import java.util.List;

import com.example.domain.FileServiceGroup;

public interface FileServiceGroupRMapper {
	
	public List<FileServiceGroup> selectAll();
	
	public List<FileServiceGroup> selectAllIgnoreEnabled();
	
	public FileServiceGroup selectById(long id);
	
	public FileServiceGroup selectByIdIgnoreEnabled(long id);

}
