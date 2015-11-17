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
import com.example.domain.FileServiceGroup;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceGroupWMapper;
import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiFileServiceGroupController {

	static final String ENABLED = "enabled";
	static final String CREATED_AT = "createdAt";
	static final String UPDATED_AT = "updatedAt";
	static final String ID = "id";
	static final String NAME = "name";

	static final String PREFIX = RouteDefine.STRING_ADMIN
			+ "/file_service_group/fsg_";
	static final String DISABLE = PREFIX + "disable";
	static final String EDIT = PREFIX + "edit";
	static final String ENABLE = PREFIX + "enable";
	static final String LIST = PREFIX + "list";
	static final String NEW = PREFIX + "new";

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
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICE_GROUPS_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, NEW);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICE_GROUPS, method = RequestMethod.POST)
	public ModelAndView newFileServiceGroup(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter(NAME);
		logger.debug("name: " + name);
		FileServiceGroup e = new FileServiceGroup();
		e.reset();
		e.setName(name);
		wMapper.insert(e);
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

}
