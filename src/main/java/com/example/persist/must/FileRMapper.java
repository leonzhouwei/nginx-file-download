package com.example.persist.must;

import java.util.List;

import com.example.domain.File;

public interface FileRMapper {
	
	public List<File> selectAll();
	
	public File selectById(long id);

}
