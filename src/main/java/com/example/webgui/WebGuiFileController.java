package com.example.webgui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.DateTimeTool;
import com.example.domain.File;
import com.example.persist.mapper.FileRMapper;
import com.example.web.RouteDefine;

@Controller
public class WebGuiFileController {
	
	static final String FILE = "file/";
	static final String FILE_LIST = FILE + "file_list";
	static final String FILE_DETAIL = FILE + "file_detail";

	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.FILES, method = RequestMethod.GET)
	public String list() {
		return FILE_LIST;
	}

	@RequestMapping(value = RouteDefine.FILES + "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(HttpServletRequest request,
			@PathVariable String id, HttpServletResponse response) {
		File file = rMapper.selectById(Long.parseLong(id));
		if (file == null) {
			return WebGuiNotFoundController.newModelAndView(response);
		}
		ModelAndView ret = new ModelAndView(FILE_DETAIL);
		ret.getModel().put("id", file.getId());
		ret.getModel().put("name", file.getName());
		ret.getModel().put("size", file.getSize());
		ret.getModel().put("sizeMb", file.getSize() / 1024 / 1024);
		ret.getModel().put("createdAt",
				DateTimeTool.toLocal(file.getCreatedAt()));
		return ret;
	}

}
