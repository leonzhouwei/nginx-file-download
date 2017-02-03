package com.example.webapi.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.DownloadTask;
import com.example.persist.must.DownloadTaskRMapper;
import com.example.persist.must.DownloadTaskWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminDownloadTaskApi {

	@Autowired
	private DownloadTaskRMapper rMapper;
	@Autowired
	private DownloadTaskWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN + "/dld-tasks", method = RequestMethod.GET)
	public void getAll(HttpServletResponse response) {
		List<DownloadTask> tasks = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, tasks);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN + "/dld-tasks/{id}/actions/disable", method = RequestMethod.POST)
	public void disable(HttpServletResponse response, @PathVariable Long id) {
		DownloadTask e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN + "/dld-tasks/{id}/actions/enable", method = RequestMethod.POST)
	public void enable(HttpServletResponse response, @PathVariable Long id) {
		DownloadTask e = rMapper.selectById(id);
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.enable();
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
