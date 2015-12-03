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
import com.example.common.ReflectTool;
import com.example.config.AppConfig;
import com.example.domain.FileService;
import com.example.domain.FileServiceGroup;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceRMapper;
import com.example.persist.must.FileServiceWMapper;
import com.example.webapi.RouteDefine;
import com.example.webgui.WebGuiDefine;
import com.google.common.base.Strings;

@Controller
public class AdminWebGuiFileServiceController {

	static final String GROUPS = "groups";
	static final String HOST = "host";
	static final String GROUP_ID = "groupId";

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN
			+ "/file-service/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX
			+ WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX
			+ WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminWebGuiFileServiceController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileServiceRMapper rMapper;
	@Autowired
	private FileServiceWMapper wMapper;
	@Autowired
	private FileServiceGroupRMapper fsgRMapper;

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICES_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICES, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getParameter(HOST);
		logger.debug("host: " + host);
		String groupIdStr = request.getParameter(GROUP_ID);
		logger.debug("group id: " + groupIdStr);
		FileService e = new FileService();
		e.reset();
		e.setHost(host);
		e.setGroupId(Long.parseLong(groupIdStr));
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILE_SERVICES);
	}
	
	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICES_EDIT, method = RequestMethod.GET)
	public ModelAndView gotoEdit(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		FileService e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig,
				VIEW_NAME_EDIT);
		ret.getModel().putAll(ReflectTool.toMap(e));
		return ret;
	}
	
	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICES_EDIT, method = RequestMethod.POST)
	public ModelAndView edit(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = HttpRequestTool.extractId(request);
		if (id == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		FileService e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		String host = request.getParameter(HOST);
		if (!Strings.isNullOrEmpty(host)) {
			e.setHost(host);
		}
		Long groupId = HttpRequestTool.extractLong(request, GROUP_ID);
		if (groupId != null) {
			FileServiceGroup fsg = fsgRMapper.selectByIdIgnoreEnabled(groupId);
			if (fsg == null) {
				return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
			}
			e.setGroupId(groupId);
		}
		Boolean enabled = HttpRequestTool.extractEnabled(request);
		if (enabled != null) {
			e.setEnabled(enabled);
		}
		e.resetUpdatedAt();
		wMapper.update(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILE_SERVICES);
	}

}
