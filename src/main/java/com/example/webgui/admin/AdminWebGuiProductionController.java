package com.example.webgui.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.Production;
import com.example.persist.rdbms.ProductionWMapper;
import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiProductionController {

	static final String LIST = "admin/prod_list";
	static final String NEW = "admin/prod_new";

	private static final Logger logger = LoggerFactory
			.getLogger(AdminWebGuiProductionController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private ProductionWMapper wMapper;

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNewProductionPage() {
		return ModelAndViewTool.newModelAndView(appConfig, NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.POST)
	public ModelAndView newProduction(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		logger.debug("name: " + name);
		String description = request.getParameter("description");
		logger.debug("description: " + description);
		Production e = new Production();
		e.reset();
		e.setName(name);
		e.setDescription(description);
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}
	
}
