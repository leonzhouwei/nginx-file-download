package com.example.webgui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.File;
import com.example.persist.must.FileRMapper;
import com.example.webapi.RouteDefine;

@Controller
public class FileController {

	static final String VIEW_NAME_PREFIX = "file/";
	static final String VIEW_NAME_DETAIL = VIEW_NAME_PREFIX + WebGuiDefine.DETAIL;
	static final String VIEW_NAME_LIST = VIEW_NAME_PREFIX + WebGuiDefine.LIST;

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.FILES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_LIST);
	}

	@RequestMapping(value = RouteDefine.FILES + "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(HttpServletRequest request, @PathVariable Long id, HttpServletResponse response) {
		File file = rMapper.selectEnabledById(id);
		if (file == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, VIEW_NAME_DETAIL, file);
		return ret;
	}

}
