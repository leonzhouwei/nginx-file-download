package com.example.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.domain.File;

@Transactional(readOnly = true)
public interface FileRDao {
	public List<File> selectAll();

	public List<File> selectAllEnabled();

	public File selectById(long id);

	public File selectEnabledById(long id);
}
