package com.example.init;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DataSourceTransactionManagerInit implements InitializingBean {

	private DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();

	@Autowired
	private DataSource dataSource;

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return transactionManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		transactionManager.setDataSource(dataSource);
		transactionManager.afterPropertiesSet();
	}

}
