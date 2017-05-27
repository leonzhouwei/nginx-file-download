package com.example.web.gui.admin;

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
import com.example.web.consts.RouteDefine;
import com.example.web.gui.WebGuiDefine;
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

	static final String BASE_ROUTE = RouteDefine.ADMIN + "/files";

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

	@RequestMapping(value = BASE_ROUTE, method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/files/new", method = RequestMethod.GET)
	public ModelAndView gotoNew(HttpServletRequest request) {
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = BASE_ROUTE, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView ng = newOneCheck(request, response);
		if (ng != null) {
			return ng;
		}

		final String name = HttpRequestTool.extractName(request);
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		final Long size = HttpRequestTool.extractSize(request);
		String md = HttpRequestTool.extractMd(request);
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		File file = new File();
		file.reset();
		file.setDir(request.getParameter(DIR));
		file.setName(name);
		Production production = new Production();
		production.setId(productionId);
		file.setProduction(production);
		file.setSize(size);
		file.setMd(md);
		file.setEnabled(enabled);
		FileServiceGroup fsGroup = new FileServiceGroup();
		fsGroup.setId(fsgId);
		file.setFileServiceGroup(fsGroup);
		wMapper.insert(file);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

	private ModelAndView newOneCheck(HttpServletRequest request, HttpServletResponse response) {
		final String name = HttpRequestTool.extractName(request);
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		final Long size = HttpRequestTool.extractSize(request);
		String md = HttpRequestTool.extractMd(request);
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		if (Strings.isNullOrEmpty(name) || productionId == null || size == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		if (Strings.isNullOrEmpty(md) || enabled == null || fsgId == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}

		return newOneDeepCheck(request, response);
	}

	private ModelAndView newOneDeepCheck(HttpServletRequest request, HttpServletResponse response) {
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		Production prod = productionRMapper.selectById(productionId);
		if (prod == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}

		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		FileServiceGroup fsg = fsgRMapper.selectById(fsgId);
		if (fsg == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}

		return null;
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/files/edit", method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request, HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig);
		}
		return ModelAndViewTool.newModelAndView(request, appConfig, VIEW_NAME_EDIT, e);
	}

	@RequestMapping(value = RouteDefine.ADMIN + "/files/edit", method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(JsonTool.toJson(request.getParameterMap()));
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig, HttpRequestTool.ID);
		}
		File e = rMapper.selectById(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig, "file not found");
		}

		fillFile(e, request);
		ModelAndView errMav = deepFillFile(e, request, response);
		if (errMav != null) {
			return errMav;
		}

		wMapper.update(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(request, appConfig, BASE_ROUTE);
	}

	private void fillFile(File e, HttpServletRequest request) {
		String dir = request.getParameter(DIR);
		if (!Strings.isNullOrEmpty(dir)) {
			e.setDir(dir);
		}
		String name = HttpRequestTool.extractName(request);
		if (!Strings.isNullOrEmpty(name)) {
			e.setName(name);
		}
		final Long size = HttpRequestTool.extractSize(request);
		if (size != null) {
			e.setSize(size);
		}
		String md = HttpRequestTool.extractMd(request);
		if (!Strings.isNullOrEmpty(md)) {
			e.setMd(md);
		}
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		if (enabled != null) {
			e.setEnabled(enabled);
		}
	}

	private ModelAndView deepFillFile(File e, HttpServletRequest request, HttpServletResponse response) {
		final Long productionId = HttpRequestTool.extractLong(request, PRODUCTION);
		if (productionId != null) {
			Production production = productionRMapper.selectById(productionId);
			if (production == null) {
				return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig, PRODUCTION);
			}
			e.getProduction().setId(productionId);
		}
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		if (fsgId != null) {
			FileServiceGroup fsg = fsgRMapper.selectById(fsgId);
			if (fsg == null) {
				return ModelAndViewTool.newModelAndViewFor404(request, response, appConfig, FSG);
			}
			e.getFileServiceGroup().setId(fsgId);
		}
		e.resetUpdatedAt();
		return null;
	}

}
