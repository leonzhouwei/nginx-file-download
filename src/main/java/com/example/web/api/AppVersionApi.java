package com.example.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App;
import com.example.web.consts.RouteDefine;

@RestController
public class AppVersionApi {
	
	@RequestMapping(RouteDefine.API + "/version")
	public String get() {
		return App.VERSION;
	}

}
