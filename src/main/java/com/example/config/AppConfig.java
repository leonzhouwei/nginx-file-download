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

	// app ---------------------------------------------------------------------
	@Value("${app.workDir}")
	private String workDirPath;
	@Value("${app.ignoreCustomizedInterceptors}")
	private Boolean ignoreCustomizedInterceptors;
	@Value("${app.data.dir}")
	private String dataDir;

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
		if (children != null) {
			for (String e : children) {
				if (e.matches("v\\d+")) {
					list.add(e);
				}
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
