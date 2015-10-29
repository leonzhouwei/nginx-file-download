package com.example.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.init.ssdb.SsdbRDriverBean;
import com.example.init.ssdb.SsdbWDriverBean;
import com.example.persist.nosql.DownloadHistoryRMapper;
import com.example.persist.nosql.DownloadHistoryWMapper;
import com.example.persist.nosql.ssdb.SsdbDownloadHistoryRMapper;
import com.example.persist.nosql.ssdb.SsdbDownloadHistoryWMapper;

@Component
public class DownloadHistoryMapperBeansGenerator implements InitializingBean {

	private SsdbDownloadHistoryRMapper ssdbDownloadHistoryRMapper = new SsdbDownloadHistoryRMapper();
	private SsdbDownloadHistoryWMapper ssdbDownloadHistoryWMapper = new SsdbDownloadHistoryWMapper();

	@Autowired
	private SsdbRDriverBean ssdbRDriver;
	@Autowired
	private SsdbWDriverBean ssdbWDriver;

	@Override
	public void afterPropertiesSet() throws Exception {
		ssdbDownloadHistoryRMapper.setDriver(ssdbRDriver);
		ssdbDownloadHistoryWMapper.setDriver(ssdbWDriver);
	}

	@Bean
	public DownloadHistoryRMapper downloadHistoryRMapperBean() {
		return ssdbDownloadHistoryRMapper;
	}

	@Bean
	public DownloadHistoryWMapper downloadHistoryWMapperBean() {
		return ssdbDownloadHistoryWMapper;
	}

}
