package com.example.webapi.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.FileServiceGroup;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceGroupWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminFileServiceGroupApi {

	@Autowired
	private FileServiceGroupRMapper rMapper;
	@Autowired
	private FileServiceGroupWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS, method = RequestMethod.GET)
	public void list(HttpServletResponse response) {
		List<FileServiceGroup> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS + "/{id}/actions/disable", method = RequestMethod.POST)
	public void disable(HttpServletResponse response, @PathVariable Long id) {
		FileServiceGroup e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS + "/{id}/actions/enable", method = RequestMethod.POST)
	public void enable(HttpServletResponse response, @PathVariable Long id) {
		FileServiceGroup e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.enable();
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
