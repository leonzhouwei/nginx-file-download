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

@Controller
public class WebGuiFileController {

	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = WebGuiRouteDefine.FILES, method = RequestMethod.GET)
	public String list() {
		return "file/file_list";
	}

	@RequestMapping(value = WebGuiRouteDefine.FILES + "/{id}", method = RequestMethod.GET)
	public ModelAndView getById(HttpServletRequest request,
			@PathVariable String id, HttpServletResponse response) {
		File file = rMapper.selectById(Long.parseLong(id));
		if (file == null) {
			return WebGuiNotFoundController.newModelAndView();
		}
		ModelAndView ret = new ModelAndView("file/file_detail");
		ret.getModel().put("id", file.getId());
		ret.getModel().put("name", file.getName());
		ret.getModel().put("size", file.getSize());
		ret.getModel().put("createdAt",
				DateTimeTool.toLocal(file.getCreatedAt()));
		return ret;
	}

}
