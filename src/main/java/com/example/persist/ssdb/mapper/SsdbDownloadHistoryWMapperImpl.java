package com.example.persist.ssdb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.DownloadHistory;
import com.example.persist.mapper.DownloadHistoryWMapper;
import com.example.persist.ssdb.driver.SsdbWDriver;
import com.example.util.JsonTool;
import com.google.common.base.Joiner;

@Component
public class SsdbDownloadHistoryWMapperImpl implements DownloadHistoryWMapper {

	@Autowired
	private SsdbWDriver driver;

	static final String keyFor(DownloadHistory e) {
		StringBuilder sb = new StringBuilder();
		Long taskId = e.getTaskId();
		Joiner.on(SsdbDefine.SEGMENT_SEPARATOR).appendTo(sb, SsdbDefine.TASK,
				taskId, SsdbDefine.DOWNLOAD_HISTORY);
		return sb.toString();
	}

	@Override
	public void insert(DownloadHistory e) {
		String key = keyFor(e);
		Long id = driver.incrByOne(key);
		e.setId(id);
		String json = JsonTool.toJson(e);
		driver.hset(key, id, json);
	}

}
