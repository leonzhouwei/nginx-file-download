package com.example.webapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.AppConfig;

@RestController
public class AppUuidApi {
	
	@RequestMapping(RouteDefine.API + "/uuid")
	public String get() {
		return AppConfig.APP_UUID;
	}
	
}
