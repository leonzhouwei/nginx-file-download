package com.example.persist.must;

import com.example.domain.FileServiceGroup;

public interface FileServiceGroupWMapper {
	
	public void insert(FileServiceGroup e);
	
	public void enable(FileServiceGroup e);
	
	public void disable(FileServiceGroup e);
	
	public void update(FileServiceGroup e);
	
	public void delete(FileServiceGroup e);

}
