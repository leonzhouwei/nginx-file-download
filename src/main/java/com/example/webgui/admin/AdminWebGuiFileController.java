package com.example.webgui.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.File;
import com.example.persist.must.FileRMapper;
import com.example.persist.must.FileWMapper;
import com.example.webapi.RouteDefine;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

@Controller
public class AdminWebGuiFileController {
	
	static final String ID = "id";
	static final String NAME = "name";
	static final String ENABLED = "enabled";

	static final String PREFIX = RouteDefine.STRING_ADMIN + "/file/file_";
	static final String DISABLE = PREFIX + "disable";
	static final String EDIT = PREFIX + "edit";
	static final String ENABLE = PREFIX + "enable";
	static final String LIST = PREFIX + "list";
	static final String NEW = PREFIX + "new";

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper rMapper;
	@Autowired
	private FileWMapper wMapper;

	static Map<String, Object> toMap(File e) {
		Map<String, Object> ret = Maps.newHashMap();
		ret.put(ID, e.getId());
		ret.put(NAME, e.getName());
		ret.put(ENABLED, e.getEnabled());
		return ret;
	}

	static void addAllObjects(ModelAndView mav, File e) {
		mav.addAllObjects(toMap(e));
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_EDIT, method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, EDIT);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String name = request.getParameter(NAME);
		if (!Strings.isNullOrEmpty(name)) {
			e.setName(name);
		}
		String enabledStr = request.getParameter(ENABLED);
		if (!Strings.isNullOrEmpty(enabledStr)) {
			e.setEnabled(Boolean.valueOf(enabledStr));
		}
		e.resetUpdatedAt();
		wMapper.update(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_DISABLE, method = RequestMethod.GET)
	public ModelAndView gotoDisable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, DISABLE);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_DISABLE, method = RequestMethod.POST)
	public ModelAndView disable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.disable();
		wMapper.disable(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_ENABLE, method = RequestMethod.GET)
	public ModelAndView gotoEnable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, ENABLE);
		addAllObjects(ret, e);
		return ret;
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILES_ENABLE, method = RequestMethod.POST)
	public ModelAndView enable(HttpServletRequest request,
			HttpServletResponse response) {
		String idStr = request.getParameter(ID);
		final long id = Long.parseLong(idStr);
		File e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		e.enable();
		wMapper.enable(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

}
