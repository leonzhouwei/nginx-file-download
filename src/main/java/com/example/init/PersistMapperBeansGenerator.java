package com.example.init;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.example.persist.assist.DownloadHistoryRMapper;
import com.example.persist.assist.DownloadHistoryWMapper;

public interface PersistMapperBeansGenerator extends InitializingBean,
		DisposableBean {

	public DownloadHistoryRMapper downloadHistoryRMapper();

	public DownloadHistoryWMapper downloadHistoryWMapper();

}
