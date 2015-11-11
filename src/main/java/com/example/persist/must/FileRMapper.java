package com.example.persist.must;

import java.util.List;

import com.example.domain.File;

public interface FileRMapper {
	
	public List<File> selectAll();
	
	public List<File> selectAllIgnoreEnabled();
	
	public File selectById(long id);
	
	public File selectByIdIgnoreEnabled(long id);

}
