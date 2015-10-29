package com.example.persist.nosql.ssdb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.JsonTool;
import com.example.domain.DownloadHistory;
import com.example.persist.mapper.nosql.DownloadHistoryWMapper;
import com.example.persist.nosql.ssdb.driver.SsdbWDriver;

@Component
public class SsdbDownloadHistoryWMapperImpl implements DownloadHistoryWMapper {

	@Autowired
	private SsdbWDriver driver;

	@Override
	public void insert(DownloadHistory e) {
		String idGenKey = SsdbDownloadHistoryRMapperImpl.idGenKeyFor(e);
		Long id = driver.incrByOne(idGenKey);
		e.setId(id);
		String json = JsonTool.toJson(e);
		String key = SsdbDownloadHistoryRMapperImpl.keyFor(e);
		driver.hset(key, id, json);
	}

}
