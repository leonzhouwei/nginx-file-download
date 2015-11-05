package com.example.webgui.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiProductionController {

	static final String LIST = "admin/prod_list";

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.GET)
	public String list() {
		return LIST;
	}
}
