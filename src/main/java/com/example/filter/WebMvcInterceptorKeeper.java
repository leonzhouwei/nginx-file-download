package com.example.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.config.AppConfig;

@Component
public class WebMvcInterceptorKeeper extends WebMvcConfigurerAdapter {
	
	@Autowired
	private AppConfig appConfig;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		if (appConfig.getIgnoreCustomizedInterceptors()) {
			return;
		}
		registry.addInterceptor(new LoginInterceptor());
	}

}
