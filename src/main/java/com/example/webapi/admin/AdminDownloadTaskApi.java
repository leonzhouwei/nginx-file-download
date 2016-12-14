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

	@RequestMapping(value = RouteDefine.API_ADMIN_DOWNLOAD_TASKS, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<DownloadTask> tasks = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, tasks);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_DOWNLOAD_TASKS + "/{id}/actions/delete", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		DownloadTask task = rMapper.selectById(id);
		if (task == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		wMapper.delete(task);
		HttpResponseTool.writeResponse(response, task);
	}

}
