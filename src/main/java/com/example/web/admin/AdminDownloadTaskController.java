package com.example.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletResponseUtil;
import com.example.domain.DownloadTask;
import com.example.persist.mapper.DownloadTaskRMapper;
import com.example.web.RouteDefine;

@RestController
public class AdminDownloadTaskController {

	@Autowired
	private DownloadTaskRMapper rMapper;

	@RequestMapping(value = RouteDefine.ADMIN_API_DOWNLOAD_TASKS, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<DownloadTask> tasks = rMapper.selectAll();
		HttpServletResponseUtil.writeResponse(response, tasks);
	}

}
