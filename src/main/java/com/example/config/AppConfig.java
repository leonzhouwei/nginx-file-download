package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

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

}
