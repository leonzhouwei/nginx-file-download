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
	@Value("${app.ignoreCustomizedIntegererceptors}")
	private Boolean ignoreCustomizedIntegererceptors;
	@Value("${app.data.dir}")
	private String dataDir;

	private String assetsHome;

	// rdbms -------------------------------------------------------------------
	@Value("${app.db.driverClassName}")
	private String rdbmsDriverClassName;
	@Value("${app.db.initialSize}")
	private Integer rdbmsInitialSize;
	@Value("${app.db.maxActive}")
	private Integer rdbmsMaxActive;
	@Value("${app.db.minIdle}")
	private Integer rdbmsMinIdle;
	@Value("${app.db.maxIdle}")
	private Integer rdbmsMaxIdle;
	@Value("${app.db.removeAbandoned}")
	private Boolean rdbmsRemoveAbandoned;
	@Value("${app.db.removeAbandonedTimeout}")
	private Integer rdbmsRemoveAbandonedTimeout;
	@Value("${app.db.maxWait}")
	private Integer rdbmsMaxWait;
	@Value("${app.db.validationQuery}")
	private String rdbmsValidationQuery;
	@Value("${app.db.testOnBorrow}")
	private Boolean rdbmsTestOnBorrow;
	@Value("${app.db.url}")
	private String rdbmsUrl;
	@Value("${app.db.username}")
	private String rdbmsUsername;
	@Value("${app.db.password}")
	private String rdbmsPassword;

	// nginx -------------------------------------------------------------------
	@Value("${app.nginx.xAccelPrefix}")
	private String xAccelPrefix;

	@Override
	public String toString() {
		return "AppConfig [workDirPath=" + workDirPath + ", ignoreCustomizedIntegererceptors="
				+ ignoreCustomizedIntegererceptors + ", dataDir=" + dataDir + ", assetsHome=" + assetsHome
				+ ", rdbmsDriverClassName=" + rdbmsDriverClassName + ", rdbmsInitialSize=" + rdbmsInitialSize
				+ ", rdbmsMaxActive=" + rdbmsMaxActive + ", rdbmsMinIdle=" + rdbmsMinIdle + ", rdbmsMaxIdle="
				+ rdbmsMaxIdle + ", rdbmsRemoveAbandoned=" + rdbmsRemoveAbandoned + ", rdbmsRemoveAbandonedTimeout="
				+ rdbmsRemoveAbandonedTimeout + ", rdbmsMaxWait=" + rdbmsMaxWait + ", rdbmsValidationQuery="
				+ rdbmsValidationQuery + ", rdbmsTestOnBorrow=" + rdbmsTestOnBorrow + ", rdbmsUrl=" + rdbmsUrl
				+ ", rdbmsUsername=" + rdbmsUsername + ", rdbmsPassword=" + rdbmsPassword + ", xAccelPrefix="
				+ xAccelPrefix + "]";
	}

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

	public Integer getRdbmsInitialSize() {
		return rdbmsInitialSize;
	}

	public Integer getRdbmsMaxActive() {
		return rdbmsMaxActive;
	}

	public Integer getRdbmsMinIdle() {
		return rdbmsMinIdle;
	}

	public Integer getRdbmsMaxIdle() {
		return rdbmsMaxIdle;
	}

	public Boolean isRdbmsRemoveAbandoned() {
		return rdbmsRemoveAbandoned;
	}

	public Integer getRdbmsRemoveAbandonedTimeout() {
		return rdbmsRemoveAbandonedTimeout;
	}

	public Integer getRdbmsMaxWait() {
		return rdbmsMaxWait;
	}

	public String getRdbmsValidationQuery() {
		return rdbmsValidationQuery;
	}

	public Boolean isRdbmsTestOnBorrow() {
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

	public Boolean getIgnoreCustomizedIntegererceptors() {
		return ignoreCustomizedIntegererceptors;
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
