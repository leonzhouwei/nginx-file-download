package com.example.persist.must;

import java.util.List;

import com.example.domain.FileServiceGroup;

public interface FileServiceGroupRMapper {
	
	public List<FileServiceGroup> selectAll();
	
	public FileServiceGroup selectById(long id);

}
