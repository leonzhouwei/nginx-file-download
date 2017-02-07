package com.example;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.config.AppConfig;

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
		logger.info("config: " + appConfig);
		logger.info("current working directory: '" + AppConfig.CURRENT_WORKING_DIR + "'");
		logger.info("----- Status Info  END  ----- ");
	}
}
