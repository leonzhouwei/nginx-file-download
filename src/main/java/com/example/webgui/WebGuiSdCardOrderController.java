package com.example.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.web.RouteDefine;

@Controller
public class WebGuiSdCardOrderController {

	@RequestMapping(value = RouteDefine.I_SD_CARD_ORDERS, method = RequestMethod.GET)
	public String list() {
		return "sd_card_order/sd_card_order_list";
	}

}
