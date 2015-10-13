package com.example.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.DownloadHistory;
import com.example.domain.DownloadTask;
import com.example.persist.mapper.DownloadHistoryRMapper;
import com.example.persist.mapper.DownloadTaskRMapper;
import com.example.persist.mapper.DownloadTaskWMapper;
import com.example.service.DownloadTaskService;
import com.google.common.collect.Lists;

@Component
public class DownloadTaskServiceImpl implements DownloadTaskService {

	@Autowired
	private DownloadTaskRMapper taskRMapper;
	@Autowired
	private DownloadTaskWMapper taskWMapper;
	@Autowired
	private DownloadHistoryRMapper historyRMapper;

	public static long calculateTimeCostInMillis(long taskId,
			List<DownloadHistory> historyList) {
		// filter
		List<DownloadHistory> sorted = Lists.newArrayList();
		for (DownloadHistory e : historyList) {
			if (e.getTaskId() != taskId) {
				continue;
			}
			sorted.add(e);
		}
		if (sorted.isEmpty()) {
			return -1;
		}
		Collections.sort(sorted, new Comparator<DownloadHistory>() {
			@Override
			public int compare(DownloadHistory o1, DownloadHistory o2) {
				final long createdAt1 = o1.getCreatedAt().getTime();
				final long createdAt2 = o2.getCreatedAt().getTime();
				final long delta = createdAt1 - createdAt2;
				if (delta < 0) {
					return -1;
				}
				if (delta == 0) {
					return 0;
				}
				return 1;
			}
		});
		final long startMillis = sorted.get(0).getCreatedAt().getTime();
		final long endMillis = sorted.get(sorted.size()-1).getCreatedAt().getTime();
		final long ret = endMillis - startMillis;
		return ret;
	}

	long updateTimeCostMillis(long taskId) {
		DownloadTask task = taskRMapper.selectById(taskId);
		if (task == null) {
			return -1;
		}
		List<DownloadHistory> historyList = historyRMapper
				.selectByTaskId(taskId);
		if (historyList.isEmpty()) {
			return 0;
		}
		final long timeCostMillis = calculateTimeCostInMillis(taskId, historyList);
		task.setTimeCostMillis(timeCostMillis);
		taskWMapper.updateTimeCostMillis(task);
		return timeCostMillis;
	}

}
