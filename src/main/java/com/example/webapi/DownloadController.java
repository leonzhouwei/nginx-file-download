package com.example.webapi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.HttpDefine;
import com.example.common.HttpServletRequestTool;
import com.example.common.HttpServletResponseUtil;
import com.example.common.JsonTool;
import com.example.config.AppConfig;
import com.example.domain.DownloadHistory;
import com.example.domain.DownloadTask;
import com.example.domain.File;
import com.example.domain.FileService;
import com.example.domain.FileServiceGroup;
import com.example.filter.LoginInterceptor;
import com.example.persist.assist.DownloadHistoryWMapper;
import com.example.persist.must.DownloadTaskRMapper;
import com.example.persist.must.DownloadTaskWMapper;
import com.example.persist.must.FileRMapper;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceRMapper;
import com.google.common.base.Strings;

@Controller
public class DownloadController {

	static final String WEB_SERVER_HOST = "Host";
	static final String WEB_SERVER_X_ACCEL = "X-Accel-Redirect";
	static final String ATACHMENT_FILENAME = "attachment;filename=";

	static final String COLON = ":";
	static final String SLASH = "/";
	static final String FILE_SEPARATOR = "/";
	static final String regEx = "[\u4e00-\u9fa5]";
	static final Pattern pat = Pattern.compile(regEx);
	static final String UTF_8_CHARSET_NAME = StandardCharsets.UTF_8.name();
	static final String LOCALHOST_IPV4 = "127.0.0.1";
	
	static final String FILE_ID = "fileId";
	static final String UUID = "uuid";
	
	private static class Result {
		public boolean ok = false;
		public String clientIp;
		public String webServerHost;
		public String requestRoute;
		public String taskUuid;
		public File file;
		public FileServiceGroup fileServiceGroup;
	}

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private DownloadTaskRMapper taskRMapper;
	@Autowired
	private DownloadTaskWMapper taskWMapper;
	@Autowired
	private FileRMapper fileRMapper;
	@Autowired
	private DownloadHistoryWMapper historyWMapper;
	@Autowired
	private FileServiceGroupRMapper fileServiceGroupRMapper;
	@Autowired
	private FileServiceRMapper fileServiceRMapper;

	private static final Logger logger = LoggerFactory
			.getLogger(DownloadController.class);

	static final String xAccelRedirect(String routePrefix, File file)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append(routePrefix);
		if (!routePrefix.endsWith(SLASH)) {
			sb.append(FILE_SEPARATOR);
		}
		sb.append(file.getProductionId());
		sb.append(FILE_SEPARATOR);
		String fileDir = file.getDir();
		if (!Strings.isNullOrEmpty(fileDir)) {
			if (fileDir.startsWith(FILE_SEPARATOR)) {
				fileDir = fileDir.substring(1);
			}
			if (fileDir.endsWith(FILE_SEPARATOR)) {
				fileDir = fileDir.substring(0, fileDir.length() - 1);
			}
			sb.append(fileDir);
			sb.append(FILE_SEPARATOR);
		}
		sb.append(URLEncoder.encode(file.getName(), UTF_8_CHARSET_NAME));
		return sb.toString();
	}

	public static boolean containChinese(String str) {
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}

	@RequestMapping(RouteDefine.FILE_SERVICE_GROUPS
			+ "/{fsGroupId}/download/**")
	public void downloadWithAuth(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			UnknownHostException {
		Result result = logAndCheck(request, response);
		if (!result.ok) {
			return;
		}
		// try to find the download task by uuid
		DownloadTask task = taskRMapper.selectByUuid(result.taskUuid);
		if (task == null) {
			if (LoginInterceptor.getAccountId(request) == null) {
				HttpServletResponseUtil.setStatusAsUnauthorized(response);
				return;
			}
			// not found, create a new one
			task = new DownloadTask();
			task.reset();
			task.setFileId(result.file.getId());
			task.setClientIp(result.clientIp);
			task.setUserId(LoginInterceptor.getAccountId(request));
			task.setUuid(result.taskUuid);
			taskWMapper.insert(task);
		}
		String fileName = result.file.getName();
		// respond
		response.setContentType(HttpDefine.CONTENT_TYPE_VALUE_APP_OCTETSTREAM);
		String xAccelRoutePrefix = result.fileServiceGroup.getXAccelPrefix();
		String xAccelRedirect = xAccelRedirect(xAccelRoutePrefix, result.file);
		logger.info(WEB_SERVER_X_ACCEL + ": " + xAccelRedirect);
		response.setHeader(WEB_SERVER_X_ACCEL, xAccelRedirect);
		response.addHeader(HttpDefine.CONTENT_DISPOSITION, ATACHMENT_FILENAME
				+ URLEncoder.encode(fileName, UTF_8_CHARSET_NAME));
		// record this download request
		DownloadHistory history = new DownloadHistory();
		history.reset();
		history.setTaskId(task.getId());
		history.setClientIp(result.clientIp);
		history.setWebServerHost(result.webServerHost);
		history.setRequestRoute(result.requestRoute);
		history.setRequestParameters(JsonTool.toJson(request.getParameterMap()));
		historyWMapper.insert(history);
	}
	
	Result logAndCheck(HttpServletRequest request,
			HttpServletResponse response) throws UnknownHostException {
		final String route = request.getRequestURI();
		logger.info("route: " + route);
		String tempHost = request.getHeader(WEB_SERVER_HOST);
		final int indexOfColon = tempHost.indexOf(COLON);
		if (indexOfColon > 0) {
			tempHost = tempHost.substring(0, indexOfColon);
		}
		final String host = tempHost;
		logger.info("web server host: " + host);
		final String clientIp = HttpServletRequestTool.getClientIp(request);
		logger.info("client IP: " + clientIp);
		logger.info("X-Forwarded-For: " + request.getHeader(HttpDefine.XFF));
		logger.info("request parameters: "
				+ JsonTool.toJson(request.getParameterMap()));
		//
		String fileIdStr = request.getParameter(FILE_ID);
		if (Strings.isNullOrEmpty(fileIdStr)) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return new Result();
		}
		String uuid = request.getParameter(UUID);
		if (Strings.isNullOrEmpty(uuid)) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return new Result();
		}
		// find the file by id
		final long fileId = Long.parseLong(fileIdStr);
		File file = fileRMapper.selectById(fileId);
		if (file == null) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return new Result();
		}
		// check the file service group
		final long fileServiceGroupId = file.getFileServiceGroupId();
		logger.info("file service group id: " + fileServiceGroupId);
		FileServiceGroup fsg = fileServiceGroupRMapper
				.selectById(fileServiceGroupId);
		logger.info("file service group: " + JsonTool.toJson(fsg));
		if (fsg == null) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return new Result();
		}
		// check the file service
		FileService params = new FileService();
		params.setGroupId(fsg.getId());
		params.setHost(host);
		FileService fileService = fileServiceRMapper
				.selectByGroupIdAndHost(params);
		logger.info("file service: " + JsonTool.toJson(fileService));
		if (fileService == null) {
			HttpServletResponseUtil.setStatusAsNotFound(response);
			return new Result();
		}
		// success
		Result ret = new Result();
		ret.ok = true;
		ret.clientIp = clientIp;
		ret.webServerHost = host;
		ret.requestRoute = route;
		ret.taskUuid = uuid;
		ret.file = file;
		ret.fileServiceGroup = fsg;
		return ret;
	}

	void downloadLocalFile(HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug(request.getRequestURI());
		logger.debug(request.getRequestURL().toString());
		try {
			String path = "var/gopher.jpg";
			// path是指欲下载的文件的路径。
			java.io.File file = new java.io.File(path);
			logger.debug("file length: " + file.length());
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
