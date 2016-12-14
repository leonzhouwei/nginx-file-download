package com.example.persist.must;

import com.example.domain.File;

public interface FileWMapper {
	
	public void insert(File e);
	
	public void update(File e);
	
	public void enable(File e);
	
	public void disable(File e);
	
	public void delete(File e);
}
