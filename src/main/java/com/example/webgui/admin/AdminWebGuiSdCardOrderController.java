package com.example.webgui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webgui.WebGuiRouteDefine;

@Controller
public class AdminWebGuiSdCardOrderController {

	@RequestMapping(value = WebGuiRouteDefine.ADMIN_SD_CARD_ORDERS, method = RequestMethod.GET)
	public String list() {
		return "admin/sd_card_order_list";
	}

}
