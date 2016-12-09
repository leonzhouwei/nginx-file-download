package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
			logger.warn("", e);
		}
	}

	void logStatus() throws IOException {
		logger.info("----- Status Info START ----- ");
		logger.info("version: " + Version.VERSION);
		logger.info("static assests home: " + appConfig.getAssetsHome());
		String pid = System.getProperty("PID");
		String pidFilePath = AppConfig.CURRENT_WORKING_DIR
				+ AppConfig.FILE_SEPARATOR + appConfig.getWorkDirPath()
				+ AppConfig.FILE_SEPARATOR + "app.pid";
		File pidFile = new File(pidFilePath);
		if (!pidFile.getParentFile().exists()) {
			pidFile.mkdirs();
		}
		Files.write(pid, pidFile, StandardCharsets.UTF_8);
		logger.info("uuid: " + AppConfig.APP_UUID);
		logger.info("process id: " + pid);
		logger.info("current working directory: '"
				+ AppConfig.CURRENT_WORKING_DIR + "'");
		logger.info("----- Status Info  END  ----- ");
	}
}

//@Configuration
//@ImportResource("/spring/applicationContext.xml")
//class XmlImportingConfiguration {
//}

// public EmbeddedServletContainerFactory servletContainer()
// throws UnknownHostException {
// TomcatEmbeddedServletContainerFactory factory = new
// TomcatEmbeddedServletContainerFactory();
// factory.setPort(appConfig.getHttpPort());
// factory.setSessionTimeout(appConfig.getHttpSessionTimeout(),
// TimeUnit.SECONDS);
// factory.setUriEncoding(AppConfig.getCharsetName());
// return factory;
// }
