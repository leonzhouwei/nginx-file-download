package com.example.init;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperScannerConfigurerInit {
	
	private static final String BASE_PACKAGE = "com.example.persist.must";
	private static final String SQL_SESSION_FACTORY_BEAN_NAME = "sqlSessionFactory";
	
	@SuppressWarnings("unused")
	@Autowired
	private SqlSessionFactoryBean sqlSessioinFactoryBean;
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer ret = new MapperScannerConfigurer();
		ret.setBasePackage(BASE_PACKAGE);
		ret.setSqlSessionFactoryBeanName(SQL_SESSION_FACTORY_BEAN_NAME);
		return ret;
	}

}
