package com.example.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebGuiSdCardOrderController {

	@RequestMapping(value = WebGuiRouteDefine.SD_CARD_ORDERS, method = RequestMethod.GET)
	public String list() {
		return "sd_card_order/sd_card_order_list";
	}

}
