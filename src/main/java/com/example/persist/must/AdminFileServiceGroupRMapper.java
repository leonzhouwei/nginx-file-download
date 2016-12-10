package com.example.persist.must;

import java.util.List;

import com.example.domain.FileServiceGroup;

public interface AdminFileServiceGroupRMapper {
	
	public List<FileServiceGroup> selectAll();
	
	public FileServiceGroup selectById(long id);
	
}
