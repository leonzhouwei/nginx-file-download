package com.example.webapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App;

@RestController
public class AppVersionApi {
	
	@RequestMapping(RouteDefine.API + "/version")
	public String get() {
		return App.VERSION;
	}

}
