package com.example.persist.must;

import java.util.List;

import com.example.domain.DownloadTask;

public interface DownloadTaskRMapper {
	
	public List<DownloadTask> selectAll();
	
	public List<DownloadTask> selectAllEnabled();
	
	public DownloadTask selectById(long id);
	
	public DownloadTask selectEnabledById(long id);
	
	public List<DownloadTask> selectEnabledByUserId(long userId);
	
	public DownloadTask selectEnabledByUuid(String uuid);

}
