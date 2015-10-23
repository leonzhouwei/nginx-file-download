package com.example.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.RouteDefine;

@Controller
public class WebGuiDownloadTaskController {

	@RequestMapping(value = RouteDefine.I_DOWNLOAD_TASKS, method = RequestMethod.GET)
	public String get() {
		return "dld_task/dld_task_list";
	}

}
