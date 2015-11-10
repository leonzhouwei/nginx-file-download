package com.example.persist.assist;

import java.util.List;

import com.example.domain.DownloadHistory;

public interface DownloadHistoryRMapper {
	
	public List<DownloadHistory> selectAll();
	
	public List<DownloadHistory> selectByTaskId(long taskId);

}
