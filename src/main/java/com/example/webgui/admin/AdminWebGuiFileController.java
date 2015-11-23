package com.example.webgui.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpRequestTool;
import com.example.common.ModelAndViewTool;
import com.example.common.MoneyTool;
import com.example.common.ReflectTool;
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
import com.google.common.collect.Maps;

@Controller
public class AdminWebGuiFileController {

	static final String DIR = "dir";
	static final String PRODUCTION = "production";
	static final String FSG = "fsg";

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN + "/file/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX
			+ WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX
			+ WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

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

	static Map<String, Object> toMap(File e) {
		Map<String, Object> ret = Maps.newHashMap();
		ret.put(HttpRequestTool.ID, e.getId());
		ret.put(DIR, e.getDir());
		ret.put(HttpRequestTool.PRICE, e.getSdCardPriceFen());
		ret.put(HttpRequestTool.NAME, e.getName());
		ret.put(HttpRequestTool.ENABLED, e.getEnabled());
		return ret;
	}

	static void addAllObjects(ModelAndView mav, File e) {
		mav.addAllObjects(toMap(e));
	}

	static String extractDir(HttpServletRequest request) {
		return request.getParameter(DIR);
	}

	static Long extractProduction(HttpServletRequest request) {
		return Long.valueOf(request.getParameter(PRODUCTION));
	}

	static Long extractFileServiceGroup(HttpServletRequest request) {
		return Long.valueOf(request.getParameter(FSG));
	}

	static Long extractSdCardPriceFen(HttpServletRequest request) {
		String priceYuan = request.getParameter(HttpRequestTool.PRICE);
		return MoneyTool.yuanToFen(priceYuan);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request,
			HttpServletResponse response) {
		final String name = HttpRequestTool.extractName(request);
		if (Strings.isNullOrEmpty(name)) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final Long sdCardPriceFen = extractSdCardPriceFen(request);
		if (sdCardPriceFen == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final Long productionId = extractProduction(request);
		if (productionId == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		Production prod = productionRMapper.selectById(productionId);
		if (prod == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final Long size = HttpRequestTool.extractSize(request);
		if (size == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String md = HttpRequestTool.extractMd(request);
		if (Strings.isNullOrEmpty(md)) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final Boolean enabled = HttpRequestTool.extractEnabled(request);
		if (enabled == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		final Long fsgId = extractFileServiceGroup(request);
		if (fsgId == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		FileServiceGroup fsg = fsgRMapper.selectById(fsgId);
		if (fsg == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File file = new File();
		file.reset();
		file.setDir(extractDir(request));
		file.setName(name);
		file.setSdCardPriceFen(sdCardPriceFen);
		file.setProductionId(productionId);
		file.setSize(size);
		file.setMd(md);
		file.setEnabled(enabled);
		file.setFileServiceGroupId(fsgId);
		wMapper.insert(file);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILES);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_EDIT, method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig,
				VIEW_NAME_EDIT);
		ret.getModel().putAll(ReflectTool.toMap(e));
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) {
		final Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String dir = request.getParameter(DIR);
		if (!Strings.isNullOrEmpty(dir)) {
			e.setDir(dir);
		}
		String name = HttpRequestTool.extractName(request);
		if (!Strings.isNullOrEmpty(name)) {
			e.setName(name);
		}
		final Long sdCardPriceFen = HttpRequestTool
				.extractPriceFromYuanToFen(request);
		if (sdCardPriceFen != null) {
			e.setSdCardPriceFen(sdCardPriceFen);
		}
		final Long productionId = HttpRequestTool.extractLong(request,
				PRODUCTION);
		if (productionId != null) {
			e.setProductionId(productionId);
			Production production = productionRMapper.selectById(productionId);
			if (production == null) {
				return ModelAndViewTool.newModelAndViewFor404(appConfig,
						response);
			}
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
		final Long fsgId = HttpRequestTool.extractLong(request, FSG);
		if (fsgId != null) {
			FileServiceGroup fsg = fsgRMapper.selectById(fsgId);
			if (fsg == null) {
				return ModelAndViewTool.newModelAndViewFor404(appConfig,
						response);
			}
		}
		e.resetUpdatedAt();
		wMapper.update(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILES);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_DISABLE, method = RequestMethod.GET)
	public ModelAndView gotoDisable(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig,
				VIEW_NAME_DISABLE);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_DISABLE, method = RequestMethod.POST)
	public ModelAndView disable(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.disable();
		wMapper.disable(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILES);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_ENABLE, method = RequestMethod.GET)
	public ModelAndView gotoEnable(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig,
				VIEW_NAME_ENABLE);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_ENABLE, method = RequestMethod.POST)
	public ModelAndView enable(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.enable();
		wMapper.enable(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILES);
	}

}
