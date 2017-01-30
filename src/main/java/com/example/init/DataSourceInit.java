package com.example.init;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.config.AppConfig;

@Configuration
public class DataSourceInit {

	@Autowired
	private AppConfig appConfig;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setUrl(appConfig.getRdbmsUrl());
		ds.setUsername(appConfig.getRdbmsPassword());
		ds.setPassword(appConfig.getRdbmsPassword());
		ds.setDriverClassName(appConfig.getRdbmsDriverClassName());
		ds.setInitialSize(appConfig.getRdbmsInitialSize());
		ds.setMinIdle(appConfig.getRdbmsMinIdle());
		ds.setMaxActive(appConfig.getRdbmsMaxActive());
		ds.setRemoveAbandoned(appConfig.isRdbmsRemoveAbandoned());
		ds.setRemoveAbandonedTimeout(appConfig.getRdbmsRemoveAbandonedTimeout());
		ds.setTestOnBorrow(appConfig.isRdbmsTestOnBorrow());
		ds.setValidationQuery(appConfig.getRdbmsValidationQuery());
		ds.setDefaultAutoCommit(false);
		return ds;
	}

}
