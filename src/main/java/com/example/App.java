package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.config.AppConfig;
import com.google.common.io.Files;

@ComponentScan
@EnableAutoConfiguration
public class App {
	
	public static final String VERSION = "0.10.1";

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static ConfigurableApplicationContext cac;
	private static App app;

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args) {
		try {
			cac = SpringApplication.run(App.class, args);
			app = cac.getBean(App.class);
			app.logStatus();
		} catch (Exception e) {
			logger.warn(StringUtils.EMPTY, e);
		}
	}

	void logStatus() throws IOException {
		logger.info("----- Status Info START ----- ");
		logger.info("version: " + VERSION);
		logger.info("static assests home: " + appConfig.getAssetsHome());
		String pid = System.getProperty("PID");
		String pidFilePath = AppConfig.CURRENT_WORKING_DIR + AppConfig.FILE_SEPARATOR + appConfig.getWorkDirPath()
				+ AppConfig.FILE_SEPARATOR + "app.pid";
		File pidFile = new File(pidFilePath);
		if (!pidFile.getParentFile().exists()) {
			pidFile.mkdirs();
		}
		Files.write(pid, pidFile, StandardCharsets.UTF_8);
		logger.info("uuid: " + AppConfig.APP_UUID);
		logger.info("process id: " + pid);
		logger.info("current working directory: '" + AppConfig.CURRENT_WORKING_DIR + "'");
		logger.info("----- Status Info  END  ----- ");
	}
}
