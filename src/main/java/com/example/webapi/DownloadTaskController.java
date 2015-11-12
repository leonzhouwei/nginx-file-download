package com.example.webapi;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletRequestTool;
import com.example.common.HttpServletResponseUtil;
import com.example.domain.DownloadTask;
import com.example.domain.File;
import com.example.filter.LoginInterceptor;
import com.example.persist.must.DownloadTaskRMapper;
import com.example.persist.must.DownloadTaskWMapper;
import com.example.persist.must.FileRMapper;

@RestController
public class DownloadTaskController {

	static final String ID = "id";

	@Autowired
	private DownloadTaskRMapper rMapper;
	@Autowired
	private DownloadTaskWMapper wMapper;
	@Autowired
	private FileRMapper fileRMapper;

	@RequestMapping(value = RouteDefine.API_I_DOWNLOAD_TASKS, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		Long userId = LoginInterceptor.getAccountId(request);
		List<DownloadTask> tasks = rMapper.selectByUserId(userId);
		HttpServletResponseUtil.writeResponse(response, tasks);
	}

	@RequestMapping(value = RouteDefine.API_I_DOWNLOAD_TASKS, method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response)
			throws UnknownHostException {
		String fileIdStr = request.getParameter(ID);
		final long fileId = Long.parseLong(fileIdStr);
		File file = fileRMapper.selectById(fileId);
		if (file == null) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return;
		}
		DownloadTask task = new DownloadTask();
		task.reset();
		task.setClientIp(HttpServletRequestTool.getClientIpNullToEmpty(request));
		task.setFileId(fileId);
		task.setUserId(LoginInterceptor.getAccountId(request));
		wMapper.insert(task);
		HttpServletResponseUtil.writeResponse(response, task);
	}

}
