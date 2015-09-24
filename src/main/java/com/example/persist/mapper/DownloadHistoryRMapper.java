package com.example.persist.mapper;

import java.util.List;

import com.example.domain.DownloadHistory;

public interface DownloadHistoryRMapper {
	
	public List<DownloadHistory> selectAll();
	
	public List<DownloadHistory> selectByTaskId(long taskId);

}
