package com.example.persist.rdbms;

import java.util.List;

import com.example.domain.DownloadTask;

public interface DownloadTaskRMapper {
	
	public List<DownloadTask> selectAll();
	
	public DownloadTask selectById(long id);
	
	public List<DownloadTask> selectByUserId(long userId);
	
	public DownloadTask selectByUuid(String uuid);

}
