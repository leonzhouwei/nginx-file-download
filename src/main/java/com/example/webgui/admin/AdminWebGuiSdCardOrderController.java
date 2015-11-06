package com.example.webgui.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiSdCardOrderController {
	
	static final String LIST = "admin/sd_card_order_list";

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = RouteDefine.ADMIN_SD_CARD_ORDERS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

}
