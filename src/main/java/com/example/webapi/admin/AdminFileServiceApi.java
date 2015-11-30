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
import com.example.domain.FileService;
import com.example.persist.must.FileServiceRMapper;
import com.example.persist.must.FileServiceWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminFileServiceApi {

	@Autowired
	private FileServiceRMapper rMapper;
	@Autowired
	private FileServiceWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICES, method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List<FileService> list = rMapper.selectAllIgnoreEnabled();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICES
			+ "/{id}/disable", method = RequestMethod.POST)
	public void disable(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		FileService e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICES
			+ "/{id}/enable", method = RequestMethod.POST)
	public void enable(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Long id) {
		FileService e = rMapper.selectByIdIgnoreEnabled(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.enable();
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
