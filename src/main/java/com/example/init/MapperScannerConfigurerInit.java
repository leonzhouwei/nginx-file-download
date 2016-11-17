package com.example.init;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperScannerConfigurerInit {
	
	@SuppressWarnings("unused")
	@Autowired
	private SqlSessionFactoryBean sqlSessioinFactoryBean;
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer ret = new MapperScannerConfigurer();
		ret.setBasePackage("com.example.persist.must");
		ret.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return ret;
	}

}
