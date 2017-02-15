package com.example.web.api.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.File;
import com.example.persist.must.FileRMapper;
import com.example.persist.must.FileWMapper;
import com.example.web.consts.RouteDefine;

@RestController
public class AdminFileApi {

	@Autowired
	private FileRMapper rMapper;
	@Autowired
	private FileWMapper wMapper;

	@RequestMapping(value = RouteDefine.ADMIN_API + "/files", method = RequestMethod.GET)
	public void getAll(HttpServletResponse response) {
		List<File> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.ADMIN_API + "/files/{id}/actions/disable", method = RequestMethod.POST)
	public void disable(HttpServletResponse response, @PathVariable Long id) {
		File e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.ADMIN_API + "/files/{id}/actions/enable", method = RequestMethod.POST)
	public void enable(HttpServletResponse response, @PathVariable Long id) {
		File e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.enable();
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
