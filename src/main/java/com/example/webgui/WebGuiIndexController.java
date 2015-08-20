package com.example.webgui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebGuiIndexController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
