package com.example.persist.nosql.ssdb;

import com.example.common.JsonTool;
import com.example.domain.DownloadHistory;
import com.example.persist.nosql.DownloadHistoryWMapper;
import com.example.persist.nosql.ssdb.driver.SsdbWDriver;

public class SsdbDownloadHistoryWMapper implements DownloadHistoryWMapper {

	private SsdbWDriver driver;

	@Override
	public void insert(DownloadHistory e) {
		String idGenKey = SsdbDownloadHistoryRMapper.idGenKeyFor(e);
		Long id = driver.incrByOne(idGenKey);
		e.setId(id);
		String json = JsonTool.toJson(e);
		String key = SsdbDownloadHistoryRMapper.keyFor(e);
		driver.hset(key, id, json);
	}

	public SsdbWDriver getDriver() {
		return driver;
	}

	public void setDriver(SsdbWDriver driver) {
		this.driver = driver;
	}

}
