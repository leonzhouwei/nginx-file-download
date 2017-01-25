package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dao.FileRDao;
import com.example.domain.File;
import com.example.persist.must.FileRMapper;

@Component
public class FileRDaoImpl implements FileRDao {
	
	@Autowired
	private FileRMapper rMapper;

	@Override
	public List<File> selectAll() {
		return rMapper.selectAll();
	}

	@Override
	public List<File> selectAllEnabled() {
		return rMapper.selectAllEnabled();
	}

	@Override
	public File selectById(long id) {
		return rMapper.selectById(id);
	}

	@Override
	public File selectEnabledById(long id) {
		return rMapper.selectEnabledById(id);
	}

}
