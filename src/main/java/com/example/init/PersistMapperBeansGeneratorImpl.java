package com.example.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.config.AppConfig;
import com.example.init.ssdb.SsdbPersistMapperBeansGenerator;
import com.example.persist.assist.DownloadHistoryRMapper;
import com.example.persist.assist.DownloadHistoryWMapper;
import com.example.persist.must.DownloadTaskRMapper;
import com.example.persist.must.DownloadTaskWMapper;

@Component
public class PersistMapperBeansGeneratorImpl implements
		PersistMapperBeansGenerator {

	private SsdbPersistMapperBeansGenerator ssdbGen = new SsdbPersistMapperBeansGenerator();
	
	@Autowired
	private AppConfig appConfig;
	@Autowired
	private DownloadTaskRMapper downloadTaskRMapper;
	@Autowired
	private DownloadTaskWMapper downloadTaskWMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		ssdbGen.setAppConfig(appConfig);
		ssdbGen.setDownloadTaskRMapper(downloadTaskRMapper);
		ssdbGen.setDownloadTaskWMapper(downloadTaskWMapper);
		ssdbGen.afterPropertiesSet();
	}

	@Bean
	@Override
	public DownloadHistoryRMapper downloadHistoryRMapper() {
		return ssdbGen.downloadHistoryRMapper();
	}

	@Bean
	@Override
	public DownloadHistoryWMapper downloadHistoryWMapper() {
		return ssdbGen.downloadHistoryWMapper();
	}

	@Override
	public void destroy() throws Exception {
		ssdbGen.destroy();
	}

}
