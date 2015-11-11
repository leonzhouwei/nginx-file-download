package com.example.webgui.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.persist.must.FileRMapper;
import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiFileController {
	
	static final String FILE = "admin/";
	static final String FILE_LIST = FILE + "file_list";
	static final String FILE_DETAIL = FILE + "file_detail";

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.ADMIN_FILES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, FILE_LIST);
	}


}
