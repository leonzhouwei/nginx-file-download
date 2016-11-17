package com.example.webapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Version;

@RestController
public class AppVersionApi {
	
	@RequestMapping(RouteDefine.APP_VERSION)
	public String get() {
		System.err.println("oops: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return Version.VERSION;
	}

}
