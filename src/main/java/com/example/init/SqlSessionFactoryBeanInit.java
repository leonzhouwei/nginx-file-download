package com.example.init;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.config.AppConfig;

@Configuration
public class SqlSessionFactoryBeanInit {

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private DataSource dataSource;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean ret = new SqlSessionFactoryBean();
		ret.setDataSource(dataSource);
		String driverClassName = appConfig.getRdbmsDriverClassName();
		if ("org.sqlite.JDBC".compareTo(driverClassName) == 0) {
			ret.setTypeHandlersPackage("com.example.persist.typehandler.sqlite");
		}

		return ret;
	}

}
