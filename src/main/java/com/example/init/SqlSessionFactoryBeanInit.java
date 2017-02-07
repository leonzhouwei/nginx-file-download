package com.example.init;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.config.AppConfig;

@Configuration
public class SqlSessionFactoryBeanInit {

	private static final String SQLITE_REGEX = ".*sqlite:.*.sqlite";
	private static final String SQLITE_TYPE_HANDLERS_PACKAGE = "com.example.persist.typehandler.sqlite";

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private DataSource dataSource;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean ret = new SqlSessionFactoryBean();
		ret.setDataSource(dataSource);
		String url = appConfig.getRdbmsUrl();
		if (url.matches(SQLITE_REGEX)) {
			ret.setTypeHandlersPackage(SQLITE_TYPE_HANDLERS_PACKAGE);
		}

		return ret;
	}

}
