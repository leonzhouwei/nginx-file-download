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
import com.example.domain.FileServiceGroup;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceGroupWMapper;
import com.example.webapi.RouteDefine;
import com.example.webgui.WebGuiDefine;

@Controller
public class AdminWebGuiFileServiceGroupController {

	static final String CREATED_AT = "createdAt";
	static final String UPDATED_AT = "updatedAt";

	static final String VIEW_NAME_PREFIX = WebGuiDefine.ADMIN
			+ "/file-service-group/";
	static final String VIEW_NAME_DISABLE = VIEW_NAME_PREFIX
			+ WebGuiDefine.DISABLE;
	static final String VIEW_NAME_EDIT = VIEW_NAME_PREFIX + WebGuiDefine.EDIT;
	static final String VIEW_NAME_ENABLE = VIEW_NAME_PREFIX
			+ WebGuiDefine.ENABLE;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;
	static final String VIEW_NAME_NEW = VIEW_NAME_PREFIX + WebGuiDefine.NEW;

	private static final Logger logger = LoggerFactory
			.getLogger(AdminWebGuiFileServiceGroupController.class);

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileServiceGroupRMapper rMapper;
	@Autowired
	private FileServiceGroupWMapper wMapper;

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICE_GROUPS, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICE_GROUPS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICE_GROUPS, method = RequestMethod.POST)
	public ModelAndView newOne(HttpServletRequest request,
			HttpServletResponse response) {
		String name = HttpRequestTool.extractName(request);
		logger.debug("name: " + name);
		FileServiceGroup e = new FileServiceGroup();
		e.reset();
		e.setName(name);
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndViewAndRedirect(appConfig,
				RouteDefine.ADMIN_FILE_SERVICE_GROUPS);
	}

}
