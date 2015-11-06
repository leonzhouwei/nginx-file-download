package com.example.webgui;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.DateTimeTool;
import com.example.common.ModelAndViewTool;
import com.example.config.AppConfig;
import com.example.domain.File;
import com.example.persist.rdbms.FileRMapper;
import com.example.webapi.RouteDefine;

@Controller
public class WebGuiFileController {
	
	static final String FILE = "file/";
	static final String FILE_LIST = FILE + "file_list";
	static final String FILE_DETAIL = FILE + "file_detail";

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.FILES, method = RequestMethod.GET)
	public ModelAndView list() {
		return ModelAndViewTool.newModelAndView(appConfig, FILE_LIST);
	}

	@RequestMapping(value = RouteDefine.FILES + "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(HttpServletRequest request,
			@PathVariable String id, HttpServletResponse response) {
		File file = rMapper.selectById(Long.parseLong(id));
		if (file == null) {
			return ModelAndViewTool.newModelAndViewFor404(appConfig, response);
		}
		ModelAndView ret = ModelAndViewTool.newModelAndView(appConfig, FILE_DETAIL);
		Map<String, Object> model = ret.getModel();
		model.put("id", file.getId());
		model.put("name", file.getName());
		model.put("size", file.getSize());
		model.put("sizeMb", file.getSize() / 1024 / 1024);
		model.put("createdAt",
				DateTimeTool.toLocal(file.getCreatedAt()));
		model.put("md", file.getMd());
		return ret;
	}

}
