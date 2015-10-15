package com.example.persist.ssdb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.DownloadHistory;
import com.example.persist.mapper.DownloadHistoryWMapper;
import com.example.persist.ssdb.driver.SsdbWDriver;
import com.example.util.JsonTool;

@Component
public class SsdbDownloadHistoryWMapperImpl implements DownloadHistoryWMapper {

	@Autowired
	private SsdbWDriver driver;

	@Override
	public void insert(DownloadHistory e) {
		String key = SsdbDownloadHistoryRMapperImpl.keyFor(e);
		Long id = driver.incrByOne(key);
		e.setId(id);
		String json = JsonTool.toJson(e);
		driver.hset(key, id, json);
	}

}
