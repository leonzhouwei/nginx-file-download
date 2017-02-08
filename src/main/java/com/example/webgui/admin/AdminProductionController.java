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

import com.example.common.HttpRequestTool;
import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.Production;
import com.example.persist.must.ProductionRMapper;
import com.example.persist.must.ProductionWMapper;
import com.example.webapi.RouteDefine;
import com.example.webgui.WebGuiDefine;
import com.google.common.base.Strings;

@Controller
public class AdminProductionController {

	static final String DIR = "dir";

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN + "/production/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX + WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX + WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

	static final String BASE_ROUTE = RouteDefine.ADMIN + "/productions";

	private static final Logger logger = LoggerFactory.getLogger(AdminProductionController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private ProductionRMapper rMapper;
	@Autowired
	private ProductionWMapper wMapper;

	@RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/productions/new", method = RequestMethod.GET)
	public ModelAndView gotoNew(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = BASE_ROUTE, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request) {
		String dir = request.getParameter(DIR);
		logger.debug("dir: " + dir);
		String name = HttpRequestTool.extractName(request);
		logger.debug("name: " + name);
		String description = HttpRequestTool.extractDescription(request);
		logger.debug("description: " + description);
		Boolean enabled = HttpRequestTool.extractEnabled(request, false);
		Production e = new Production();
		e.reset();
		e.setDir(dir);
		e.setName(name);
		e.setDescription(description);
		e.setEnabled(enabled);
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/productions/edit", method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		Production e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_EDIT, e);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/productions/edit", method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		Production e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		String name = HttpRequestTool.extractName(request);
		if (!Strings.isNullOrEmpty(name)) {
			e.setName(name);
		}
		String description = HttpRequestTool.extractDescription(request);
		e.setDescription(description);
		Boolean enabled = HttpRequestTool.extractEnabled(request, false);
		e.resetUpdatedAt();
		e.setEnabled(enabled);
		wMapper.update(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

}
