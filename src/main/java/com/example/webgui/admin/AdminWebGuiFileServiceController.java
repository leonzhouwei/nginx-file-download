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
import com.example.domain.FileService;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceRMapper;
import com.example.persist.must.FileServiceWMapper;
import com.example.webapi.RouteDefine;

@Controller
public class AdminWebGuiFileServiceController {

	static final String ENABLED = "enabled";
	static final String CREATED_AT = "createdAt";
	static final String UPDATED_AT = "updatedAt";
	static final String ID = "id";
	static final String GROUPS = "groups";
	static final String HOST = "host";
	static final String GROUP_ID = "groupId";

	static final String PREFIX = RouteDefine.STRING_ADMIN + "/file_service/fs_";
	static final String DISABLE = PREFIX + "disable";
	static final String EDIT = PREFIX + "edit";
	static final String ENABLE = PREFIX + "enable";
	static final String LIST = PREFIX + "list";
	static final String NEW = PREFIX + "new";

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
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

	@RequestMapping(value = RouteDefine.ADMIN_FILE_SERVICES_NEW, method = RequestMethod.GET)
	public ModelAndView gotoNew() {
		return ModelAndViewTool.newModelAndView(appConfig, NEW);
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
		return ModelAndViewTool.newModelAndView(appConfig, LIST);
	}

}
