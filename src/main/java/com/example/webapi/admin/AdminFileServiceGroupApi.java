package com.example.webapi.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.FileServiceGroup;
import com.example.persist.must.AdminFileServiceGroupRMapper;
import com.example.persist.must.AdminFileServiceGroupWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminFileServiceGroupApi {

	@Autowired
	private AdminFileServiceGroupRMapper rMapper;
	@Autowired
	private AdminFileServiceGroupWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS, method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List<FileServiceGroup> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS
			+ "/{id}/disable", method = RequestMethod.POST)
	public void disable(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		FileServiceGroup e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS
			+ "/{id}/enable", method = RequestMethod.POST)
	public void enable(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
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
