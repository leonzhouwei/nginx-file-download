package com.example.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.AppConfig;
import com.example.web.consts.RouteDefine;

@RestController
public class AppUuidApi {
	
	@RequestMapping(RouteDefine.API + "/uuid")
	public String get() {
		return AppConfig.APP_UUID;
	}
	
}
