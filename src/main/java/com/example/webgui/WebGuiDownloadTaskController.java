package com.example.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebGuiDownloadTaskController {

	@RequestMapping(value = WebGuiRouteDefine.DOWNLOAD_TASKS, method = RequestMethod.GET)
	public String get() {
		return "dld_task/dld_task_list";
	}

}
