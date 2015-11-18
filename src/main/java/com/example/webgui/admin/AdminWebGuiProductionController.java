package com.example.webgui.admin;

import java.util.Map;

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
import com.example.persist.must.ProductionRMapper;
import com.example.persist.must.ProductionWMapper;
import com.example.webapi.RouteDefine;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

@Controller
public class AdminWebGuiProductionController {
	
	static final String ID = "id";
	static final String ENABLED = "enabled";
	static final String DESCRIPTION = "description";
	static final String NAME = "name";

	static final String PREFIX = RouteDefine.STRING_ADMIN + "/production/prod_";
	static final String DISABLE = PREFIX + "disable";
	static final String EDIT = PREFIX + "edit";
	static final String ENABLE = PREFIX + "enable";
	static final String LIST = PREFIX + "list";
	static final String NEW = PREFIX + "new";

	private static final Logger logger = LoggerFactory
			.getLogger(AdminWebGuiProductionController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private ProductionRMapper rMapper;
	@Autowired
	private ProductionWMapper wMapper;

	static Map<String, Object> toMap(Production e) {
		Map<String, Object> ret = Maps.newHashMap();
		ret.put(ID, e.getId());
		ret.put(NAME, e.getName());
		ret.put(DESCRIPTION, e.getDescription());
		ret.put(ENABLED, e.getEnabled());
		return ret;
	}

	static void addAllObjects(ModelAndView mav, Production e) {
		mav.addAllObjects(toMap(e));
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter(NAME);
		logger.debug("name: " + name);
		String description = request.getParameter(DESCRIPTION);
		logger.debug("description: " + description);
		Production e = new Production();
		e.reset();
		e.setName(name);
		e.setDescription(description);
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_EDIT, method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, EDIT);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String name = request.getParameter(NAME);
		if (!Strings.isNullOrEmpty(name)) {
			e.setName(name);
		}
		String description = request.getParameter(DESCRIPTION);
		if (!Strings.isNullOrEmpty(description)) {
			e.setDescription(description);
		}
		String enabledStr = request.getParameter(ENABLED);
		if (!Strings.isNullOrEmpty(enabledStr)) {
			e.setEnabled(Boolean.valueOf(enabledStr));
		}
		e.resetUpdatedAt();
		wMapper.update(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_DISABLE, method = RequestMethod.GET)
	public ModelAndView gotoDisable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, DISABLE);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_DISABLE, method = RequestMethod.POST)
	public ModelAndView disable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.disable();
		wMapper.disable(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_ENABLE, method = RequestMethod.GET)
	public ModelAndView gotoEnable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, ENABLE);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_PRODUCTIONS_ENABLE, method = RequestMethod.POST)
	public ModelAndView enable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		Production e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.enable();
		wMapper.enable(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

}
