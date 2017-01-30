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
import com.example.common.JsonTool;
import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.File;
import com.example.domain.FileServiceGroup;
import com.example.domain.Production;
import com.example.persist.must.FileRMapper;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileWMapper;
import com.example.persist.must.ProductionRMapper;
import com.example.webapi.RouteDefine;
import com.example.webgui.WebGuiDefine;
import com.google.common.base.Strings;

@Controller
public class AdminFileController {

	static final String DIR = "dir";
	static final String PRODUCTION = "production";
	static final String FSG = "fsg";

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN + "/file/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX + WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX + WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

	private static final Logger logger = LoggerFactory.getLogger(AdminFileController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper rMapper;
	@Autowired
	private FileWMapper wMapper;
	@Autowired
	private ProductionRMapper productionRMapper;
	@Autowired
	private FileServiceGroupRMapper fsgRMapper;

	@RequestMapping(value = RouteDefine.ADMIN_FILES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView ng = newOneCheck(request, response);
		if (ng != null) {
			return ng;
		}
		
		final String name = HttpRequestTool.extractName(request);
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		final Double size = HttpRequestTool.extractSize(request);
		String md = HttpRequestTool.extractMd(request);
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		File file = new File();
		file.reset();
		file.setDir(request.getParameter(DIR));
		file.setName(name);
		file.getProduction().setId(productionId);
		file.setSize((long) size.doubleValue());
		file.setMd(md);
		file.setEnabled(enabled);
		file.getFileServiceGroup().setId(fsgId);
		wMapper.insert(file);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig, RouteDefine.ADMIN_FILES);
	}
	
	private ModelAndView newOneCheck(HttpServletRequest request, HttpServletResponse response) {
		final String name = HttpRequestTool.extractName(request);
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		final Double size = HttpRequestTool.extractSize(request);
		String md = HttpRequestTool.extractMd(request);
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		if (Strings.isNullOrEmpty(name) || productionId == null || size == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		if (Strings.isNullOrEmpty(md) || enabled == null || fsgId == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		
		return newOneCheckWithDb(request, response);
	}
	
	private ModelAndView newOneCheckWithDb(HttpServletRequest request, HttpServletResponse response) {
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		Production prod = productionRMapper.selectById(productionId);
		if (prod == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		FileServiceGroup fsg = fsgRMapper.selectById(fsgId);
		if (fsg == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		
		return null;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_EDIT, method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request, HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_EDIT, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(JsonTool.toJson(request.getParameterMap()));
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response, HttpRequestTool.ID);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response, "file not found");
		}
		String dir = request.getParameter(DIR);
		if (!Strings.isNullOrEmpty(dir)) {
			e.setDir(dir);
		}
		String name = HttpRequestTool.extractName(request);
		if (!Strings.isNullOrEmpty(name)) {
			e.setName(name);
		}
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		if (productionId != null) {
			Production production = productionRMapper.selectById(productionId);
			if (production == null) {
				return ModelAndViewTool.newModelAndViewFor404(appConfig, response, PRODUCTION);
			}
			e.getProduction().setId(productionId);
		}
		final Double sizeMb = HttpRequestTool.extractSize(request);
		if (sizeMb != null) {
			e.resetSizeMb((long) sizeMb.doubleValue());
		}
		String md = HttpRequestTool.extractMd(request);
		if (!Strings.isNullOrEmpty(md)) {
			e.setMd(md);
		}
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		if (enabled != null) {
			e.setEnabled(enabled);
		}
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		if (fsgId != null) {
			FileServiceGroup fsg = fsgRMapper.selectById(fsgId);
			if (fsg == null) {
				return ModelAndViewTool.newModelAndViewFor404(appConfig, response, FSG);
			}
			e.getFileServiceGroup().setId(fsgId);
		}
		e.resetUpdatedAt();
		wMapper.update(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig, RouteDefine.ADMIN_FILES);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_DISABLE, method = RequestMethod.GET)
	public ModelAndView gotoDisable(HttpServletRequest request, HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_DISABLE, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_DISABLE, method = RequestMethod.POST)
	public ModelAndView disable(HttpServletRequest request, HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.disable();
		wMapper.disable(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig, RouteDefine.ADMIN_FILES);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_ENABLE, method = RequestMethod.GET)
	public ModelAndView gotoEnable(HttpServletRequest request, HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_ENABLE, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_ENABLE, method = RequestMethod.POST)
	public ModelAndView enable(HttpServletRequest request, HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.enable();
		wMapper.enable(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig, RouteDefine.ADMIN_FILES);
	}

}
