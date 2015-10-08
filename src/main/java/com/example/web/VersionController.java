package com.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Version;

@RestController
public class VersionController {
	
	@RequestMapping(RouteDefine.SYSTEM_VERSION)
	public String get() {
		return Version.VERSION;
	}

}
