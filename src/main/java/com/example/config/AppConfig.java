package com.example.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class AppConfig implements InitializingBean {

	public static class MultipleVersionedStaticResourceDirectoriesException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7742418804277082421L;

		public MultipleVersionedStaticResourceDirectoriesException(List<String> list) {
			super("multiple versioned static resources directories have been found: "
					+ StringUtils.join(list, StringUtils.SPACE));
		}
	}

	public static final String APP_UUID = UUID.randomUUID().toString().toLowerCase();

	public static final String CURRENT_WORKING_DIR = System.getProperty("user.dir");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	static final String DEV_MODE = "dev";
	static final String PROD_MODE = "prod";

	// app ---------------------------------------------------------------------
	@Value("${app.workDir}")
	private String workDirPath;
	@Value("${app.ignoreCustomizedInterceptors}")
	private Boolean ignoreCustomizedInterceptors;
	@Value("${app.data.dir}")
	private String dataDir;
	@Value("${app.download.history.disable}")
	private Boolean disableDownloadHistory;

	private String assetsHome;

	// rdbms -------------------------------------------------------------------
	@Value("${app.db.driverClassName}")
	private String rdbmsDriverClassName;
	@Value("${app.db.initialSize}")
	private int rdbmsInitialSize;
	@Value("${app.db.maxActive}")
	private int rdbmsMaxActive;
	@Value("${app.db.minIdle}")
	private int rdbmsMinIdle;
	@Value("${app.db.maxIdle}")
	private int rdbmsMaxIdle;
	@Value("${app.db.removeAbandoned}")
	private boolean rdbmsRemoveAbandoned;
	@Value("${app.db.removeAbandonedTimeout}")
	private int rdbmsRemoveAbandonedTimeout;
	@Value("${app.db.maxWait}")
	private int rdbmsMaxWait;
	@Value("${app.db.validationQuery}")
	private String rdbmsValidationQuery;
	@Value("${app.db.testOnBorrow}")
	private boolean rdbmsTestOnBorrow;
	@Value("${app.db.url}")
	private String rdbmsUrl;
	@Value("${app.db.username}")
	private String rdbmsUsername;
	@Value("${app.db.password}")
	private String rdbmsPassword;

	// nginx -------------------------------------------------------------------
	@Value("${app.nginx.xAccelPrefix}")
	private String xAccelPrefix;

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

	// app ---------------------------------------------------------------------
	public String getWorkDirPath() {
		return workDirPath;
	}

	public String getDataDir() {
		return dataDir;
	}

	public String getAssetsHome() {
		return assetsHome;
	}

	public Boolean getDisableDownloadHistory() {
		return disableDownloadHistory;
	}

	// rdbms -------------------------------------------------------------------
	public String getRdbmsDriverClassName() {
		return rdbmsDriverClassName;
	}

	public int getRdbmsInitialSize() {
		return rdbmsInitialSize;
	}

	public int getRdbmsMaxActive() {
		return rdbmsMaxActive;
	}

	public int getRdbmsMinIdle() {
		return rdbmsMinIdle;
	}

	public int getRdbmsMaxIdle() {
		return rdbmsMaxIdle;
	}

	public boolean isRdbmsRemoveAbandoned() {
		return rdbmsRemoveAbandoned;
	}

	public int getRdbmsRemoveAbandonedTimeout() {
		return rdbmsRemoveAbandonedTimeout;
	}

	public int getRdbmsMaxWait() {
		return rdbmsMaxWait;
	}

	public String getRdbmsValidationQuery() {
		return rdbmsValidationQuery;
	}

	public boolean isRdbmsTestOnBorrow() {
		return rdbmsTestOnBorrow;
	}

	public String getRdbmsUrl() {
		return rdbmsUrl;
	}

	public String getRdbmsUsername() {
		return rdbmsUsername;
	}

	public String getRdbmsPassword() {
		return rdbmsPassword;
	}

	// nginx -------------------------------------------------------------------
	public String getXAccelPrefix() {
		return xAccelPrefix;
	}

	// ssdb --------------------------------------------------------------------
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

	public Boolean getIgnoreCustomizedInterceptors() {
		return ignoreCustomizedInterceptors;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//
		final String assestsDir = dataDir + "/assets";
		File dir = new File(assestsDir);
		List<String> list = Lists.newArrayList();
		String[] children = dir.list();
		for (String e : children) {
			if (e.matches("v\\d+")) {
				list.add(e);
			}
		}
		if (list.isEmpty()) {
			throw new FileNotFoundException(
					"versioned static resources directory not found under '" + assestsDir + "'");
		}
		if (list.size() > 1) {
			throw new MultipleVersionedStaticResourceDirectoriesException(list);
		}
		assetsHome = "/assets/" + list.get(0);
	}

}
