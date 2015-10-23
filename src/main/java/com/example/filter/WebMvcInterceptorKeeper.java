package com.example.filter;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.config.AppConfig;
import com.example.persist.mapper.AccountRMapper;

@Component
public class WebMvcInterceptorKeeper extends WebMvcConfigurerAdapter implements
		InitializingBean {

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private AccountRMapper accountRMapper;

	private LoginInterceptor loginInterceptor = new LoginInterceptor();
	private AccountPrevillegeInterceptor accountPrevillegeInterceptor = new AccountPrevillegeInterceptor();

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		if (appConfig.getIgnoreCustomizedInterceptors()) {
			return;
		}
		registry.addInterceptor(loginInterceptor);
//		registry.addInterceptor(accountPrevillegeInterceptor);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loginInterceptor.setAccountRMapper(accountRMapper);
		accountPrevillegeInterceptor.setAccountRMapper(accountRMapper);
	}

}
