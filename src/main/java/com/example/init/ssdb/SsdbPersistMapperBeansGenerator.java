package com.example.init.ssdb;

import java.io.IOException;

import com.example.config.AppConfig;
import com.example.init.PersistMapperBeansGenerator;
import com.example.persist.nosql.DownloadHistoryRMapper;
import com.example.persist.nosql.DownloadHistoryWMapper;
import com.example.persist.nosql.ssdb.SsdbDownloadHistoryRMapper;
import com.example.persist.nosql.ssdb.SsdbDownloadHistoryWMapper;
import com.example.persist.rdbms.DownloadTaskRMapper;
import com.example.persist.rdbms.DownloadTaskWMapper;

public class SsdbPersistMapperBeansGenerator implements
		PersistMapperBeansGenerator {

	// external dependencies start ---------------------------------------------
	private AppConfig appConfig;
	private DownloadTaskRMapper downloadTaskRMapper;
	private DownloadTaskWMapper downloadTaskWMapper;
	// external dependencies end -----------------------------------------------
	
	private SsdbConnPoolConfigBean ssdbConnPoolConfig;
	private SsdbRDriverBean ssdbRDriver;
	private SsdbWDriverBean ssdbWDriver;

	private SsdbDownloadHistoryRMapper ssdbDownloadHistoryRMapper = new SsdbDownloadHistoryRMapper();
	private SsdbDownloadHistoryWMapper ssdbDownloadHistoryWMapper = new SsdbDownloadHistoryWMapper();

	// external dependencies start ---------------------------------------------
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	public DownloadTaskRMapper getDownloadTaskRMapper() {
		return downloadTaskRMapper;
	}

	public void setDownloadTaskRMapper(DownloadTaskRMapper downloadTaskRMapper) {
		this.downloadTaskRMapper = downloadTaskRMapper;
	}

	public DownloadTaskWMapper getDownloadTaskWMapper() {
		return downloadTaskWMapper;
	}

	public void setDownloadTaskWMapper(DownloadTaskWMapper downloadTaskWMapper) {
		this.downloadTaskWMapper = downloadTaskWMapper;
	}
	// external dependencies end -----------------------------------------------

	public SsdbRDriverBean getSsdbRDriver() {
		return ssdbRDriver;
	}

	public void setSsdbRDriver(SsdbRDriverBean ssdbRDriver) {
		this.ssdbRDriver = ssdbRDriver;
	}

	public SsdbWDriverBean getSsdbWDriver() {
		return ssdbWDriver;
	}

	public void setSsdbWDriver(SsdbWDriverBean ssdbWDriver) {
		this.ssdbWDriver = ssdbWDriver;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ssdbConnPoolConfig = new SsdbConnPoolConfigBean();
		ssdbConnPoolConfig.setAppConfig(appConfig);
		ssdbRDriver = new SsdbRDriverBean();
		ssdbRDriver.setSsdbConnPoolConfig(ssdbConnPoolConfig);
		ssdbWDriver = new SsdbWDriverBean();
		ssdbWDriver.setSsdbConnPoolConfig(ssdbConnPoolConfig);
		ssdbDownloadHistoryRMapper.setDriver(ssdbRDriver);
		ssdbDownloadHistoryRMapper.setTaskMapper(downloadTaskRMapper);
		ssdbDownloadHistoryWMapper.setDriver(ssdbWDriver);
	}

	@Override
	public DownloadHistoryRMapper downloadHistoryRMapper() {
		return ssdbDownloadHistoryRMapper;
	}

	@Override
	public DownloadHistoryWMapper downloadHistoryWMapper() {
		return ssdbDownloadHistoryWMapper;
	}

	@Override
	public void destroy() throws IOException {
		ssdbRDriver.closeSsdb();
	}

}
