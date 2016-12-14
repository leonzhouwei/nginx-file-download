package com.example.persist.must;

import com.example.domain.FileService;

public interface FileServiceWMapper {

	public void insert(FileService e);
	
	public void enable(FileService e);
	
	public void disable(FileService e);
	
	public void update(FileService e);
	
	public void delete(FileService e);
	
}
