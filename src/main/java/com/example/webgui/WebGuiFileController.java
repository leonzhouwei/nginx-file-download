package com.example.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebGuiFileController {

	@RequestMapping(value = WebGuiRouteDefine.FILE, method = RequestMethod.GET)
	public String get() {
		return "file/file_list";
	}

}
