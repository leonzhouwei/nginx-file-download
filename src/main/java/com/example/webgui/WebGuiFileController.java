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
import com.example.persist.must.FileRMapper;
import com.example.webapi.RouteDefine;
import com.google.common.collect.Maps;

@Controller
public class WebGuiFileController {
	
	static final String FILE = "file/";
	static final String FILE_LIST = FILE + "file_list";
	static final String FILE_DETAIL = FILE + "file_detail";

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileRMapper rMapper;
	
	static Map<String, Object> toMap(File file) {
		Map<String, Object> ret = Maps.newHashMap();
		ret.put("id", file.getId());
		ret.put("name", file.getName());
		ret.put("size", file.getSize());
		ret.put("sizeMb", file.getSize() / 1024 / 1024);
		ret.put("createdAt",
				DateTimeTool.toLocal(file.getCreatedAt()));
		ret.put("md", file.getMd());
		return ret;
	}
	
	static void addAllObjects(ModelAndView mav, File e) {
		mav.addAllObjects(toMap(e));
	}

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
		addAllObjects(ret, file);
		return ret;
	}

}
