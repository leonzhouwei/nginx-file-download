package com.example.config;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig implements InitializingBean {

	public static final String APP_UUID = UUID.randomUUID().toString()
			.toLowerCase();

	public static final String CURRENT_WORKING_DIR = System
			.getProperty("user.dir");
	public static final String FILE_SEPARATOR = System
			.getProperty("file.separator");

	static final String DEV_MODE = "dev";
	static final String PROD_MODE = "prod";

	// app ---------------------------------------------------------------------
	@Value("${app.runMode}")
	private String runMode;
	@Value("${app.workDir}")
	private String workDirPath;
	@Value("${app.ignoreCustomizedInterceptors}")
	private Boolean ignoreCustomizedInterceptors;
	
	private String imageDirPath;

	// ssdb --------------------------------------------------------------------
	@Value("${app.ssdb.timeout}")
	private int ssdbTimeoutMillis;
	@Value("${app.ssdb.maxActive}")
	private int ssdbMaxActive;
	@Value("${app.ssdb.testWhileIdle}")
	private boolean ssdbTestWhileIdle;
	@Value("${app.ssdb.host}")
	private String ssdbHost;
	@Value("${app.ssdb.port}")
	private int ssdbPort;
	@Value("${app.ssdb.auth}")
	private String ssdbAuth;

	// NginX -------------------------------------------------------------------
	@Value("${app.nginx.xAccel.route.prefix}")
	private String nginxXAccelRoutePrefix;

	public String getWorkDirPath() {
		return workDirPath;
	}

	public boolean isInDevelopMode() {
		if (runMode.compareTo(DEV_MODE) == 0) {
			return true;
		}
		return false;
	}

	public boolean isInProductionMode() {
		if (runMode.compareTo(PROD_MODE) == 0) {
			return true;
		}
		return false;
	}

	public String getRunMode() {
		return runMode;
	}

	public int getSsdbTimeoutMillis() {
		return ssdbTimeoutMillis;
	}

	public int getSsdbMaxActive() {
		return ssdbMaxActive;
	}

	public boolean isSsdbTestWhileIdle() {
		return ssdbTestWhileIdle;
	}

	public String getSsdbHost() {
		return ssdbHost;
	}

	public int getSsdbPort() {
		return ssdbPort;
	}

	public String getSsdbAuth() {
		return ssdbAuth;
	}

	public String getImageDirPath() {
		return imageDirPath;
	}

	public String getNginxXAccelRoutePrefix() {
		return nginxXAccelRoutePrefix;
	}

	public Boolean getIgnoreCustomizedInterceptors() {
		return ignoreCustomizedInterceptors;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties props = new Properties();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(
				"custom/config/application.properties"), StandardCharsets.UTF_8);
		props.load(isr);
		imageDirPath = props.getProperty("app.imageDirPath");
		isr.close();
	}

}
