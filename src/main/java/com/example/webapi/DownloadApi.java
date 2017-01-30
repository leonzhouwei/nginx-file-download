package com.example.webapi;

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

import com.example.common.Constants;
import com.example.common.HttpDefine;
import com.example.common.HttpRequestTool;
import com.example.common.HttpResponseTool;
import com.example.common.JsonTool;
import com.example.config.AppConfig;
import com.example.domain.DownloadHistory;
import com.example.domain.DownloadTask;
import com.example.domain.File;
import com.example.domain.FileService;
import com.example.domain.FileServiceGroup;
import com.example.domain.Production;
import com.example.filter.LoginInterceptor;
import com.example.persist.assist.DownloadHistoryWMapper;
import com.example.persist.must.DownloadTaskRMapper;
import com.example.persist.must.DownloadTaskWMapper;
import com.example.persist.must.FileRMapper;
import com.example.persist.must.FileServiceGroupRMapper;
import com.example.persist.must.FileServiceRMapper;
import com.example.persist.must.ProductionRMapper;
import com.google.common.base.Strings;

@Controller
public class DownloadApi {

	static final String WEB_SERVER_HOST = "Host";
	static final String WEB_SERVER_X_ACCEL = "X-Accel-Redirect";
	static final String ATACHMENT_FILENAME = "attachment;filename=";

	static final String EMPTY = "";
	static final String COLON = ":";
	static final String SLASH = "/";
	static final String FILE_SEPARATOR = "/";
	static final String CHINESE_CHARACTOR_REG_EX = "[\u4e00-\u9fa5]";
	static final Pattern CHINESE_CHARACTOR_PATTERN = Pattern.compile(CHINESE_CHARACTOR_REG_EX);
	static final String UTF_8_CHARSET_NAME = StandardCharsets.UTF_8.name();

	static final String FILE_ID = "fileId";
	static final String UUID = "uuid";

	static Result newStatusNgResult() {
		Result ret = new Result();
		ret.ok = false;
		return ret;
	}

	static Result newStatusOkResult() {
		Result ret = new Result();
		ret.ok = true;
		return ret;
	}

	private static class Result {
		boolean ok = false;
		String clientIp;
		String webServerHost;
		String requestRoute;
		String taskUuid;
		String fileIdStr;
		// --------------------
		Production production;
		File file;
	}

	@Autowired
	private AppConfig appConfig;
	@Autowired
	private DownloadTaskRMapper taskRMapper;
	@Autowired
	private DownloadTaskWMapper taskWMapper;
	@Autowired
	private ProductionRMapper productionRMapper;
	@Autowired
	private FileRMapper fileRMapper;
	@Autowired
	private DownloadHistoryWMapper historyWMapper;
	@Autowired
	private FileServiceGroupRMapper fileServiceGroupRMapper;
	@Autowired
	private FileServiceRMapper fileServiceRMapper;

	private static final Logger logger = LoggerFactory.getLogger(DownloadApi.class);

	static final String xAccelRedirect(final String routePrefix, Production production, File file)
			throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append(routePrefix);
		if (!routePrefix.endsWith(SLASH)) {
			sb.append(FILE_SEPARATOR);
		}
		final String prodDir = production.getDir();
		if (prodDir.startsWith(SLASH)) {
			sb.append(prodDir.substring(1));
		} else {
			sb.append(prodDir);
		}
		if (!prodDir.endsWith(SLASH)) {
			sb.append(FILE_SEPARATOR);
		}
		final String fileDir = file.getDir();
		if (!Strings.isNullOrEmpty(fileDir)) {
			if (fileDir.startsWith(SLASH)) {
				sb.append(fileDir.substring(1));
			}
			if (!fileDir.endsWith(SLASH)) {
				sb.append(FILE_SEPARATOR);
			}
		}
		sb.append(URLEncoder.encode(file.getName(), UTF_8_CHARSET_NAME));
		return sb.toString();
	}

	public static boolean containChinese(String str) {
		Matcher matcher = CHINESE_CHARACTOR_PATTERN.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}

	@RequestMapping(RouteDefine.API_FILE_SERVICE_GROUPS + "/{fsGroupId}/actions/download/**")
	public void downloadWithAuth(HttpServletRequest request, HttpServletResponse response) {
		Result result = logAndCheck(request, response);
		if (!result.ok) {
			return;
		}

		// try to find the download task by uuid
		DownloadTask task = taskRMapper.selectEnabledByUuid(result.taskUuid);
		if (task == null) {
			task = new DownloadTask();
			task.reset();
			task.getFile().setId(result.file.getId());
			task.setClientIp(result.clientIp);
			task.getUser().setId(LoginInterceptor.getAccountId(request));
			task.setUuid(result.taskUuid);
			taskWMapper.insert(task);
		} else {
			task.resetLastDldedAt();
			task.resetTimeCostMillis();
			taskWMapper.updateLastDldedAt(task);
		}
		String fileName = result.file.getName();

		// respond
		response.setContentType(HttpDefine.CONTENT_TYPE_VALUE_APP_OCTETSTREAM);
		String xAccelRoutePrefix = appConfig.getXAccelPrefix();
		String xAccelRedirect = "";
		try {
			xAccelRedirect = xAccelRedirect(xAccelRoutePrefix, result.production, result.file);
			logger.info(WEB_SERVER_X_ACCEL + ": " + xAccelRedirect);
			response.setHeader(WEB_SERVER_X_ACCEL, xAccelRedirect);
			response.addHeader(HttpDefine.CONTENT_DISPOSITION,
					ATACHMENT_FILENAME + URLEncoder.encode(fileName, UTF_8_CHARSET_NAME));
			// save the current download behavior
			recordHistory(request, task, result);
		} catch (UnsupportedEncodingException e) {
			logger.warn(Constants.EMPTY_STRING, e);
			HttpResponseTool.writeInternalServerError(response, e);
		}
	}

	Result logAndCheck(HttpServletRequest request, HttpServletResponse response) {
		// extract request route
		final String route = request.getRequestURI();
		logger.info("route: " + route);
		// extract web server host
		String tempHost = request.getHeader(WEB_SERVER_HOST);
		final int indexOfColon = tempHost.indexOf(COLON);
		if (indexOfColon > 0) {
			tempHost = tempHost.substring(0, indexOfColon);
		}
		final String host = tempHost;
		logger.info("web server host: " + host);
		// extract client's IP
		String clientIp = "";
		try {
			clientIp = HttpRequestTool.getClientIp(request);
		} catch (UnknownHostException e) {
			logger.warn(Constants.EMPTY_STRING, e);
		}
		logger.info("client IP: " + clientIp);
		logger.info("X-Forwarded-For: " + request.getHeader(HttpDefine.XFF));
		logger.info("request parameters: " + JsonTool.toJson(request.getParameterMap()));
		if (Strings.isNullOrEmpty(clientIp)) {
			HttpResponseTool.setStatusAsNotFound(response, "client IP not found");
			return newStatusNgResult();
		}
		// extract file ID
		String fileIdStr = request.getParameter(FILE_ID);
		if (Strings.isNullOrEmpty(fileIdStr)) {
			HttpResponseTool.setStatusAsNotFound(response);
			return newStatusNgResult();
		}
		// extract task UUID
		String uuid = request.getParameter(UUID);
		if (Strings.isNullOrEmpty(uuid)) {
			HttpResponseTool.setStatusAsNotFound(response);
			return newStatusNgResult();
		}

		// check with database
		Result result = new Result();
		result.clientIp = clientIp;
		result.webServerHost = host;
		result.requestRoute = route;
		result.fileIdStr = fileIdStr;
		result.taskUuid = uuid;
		return logAndCheckWithDatabase(response, result);
	}

	Result logAndCheckWithDatabase(HttpServletResponse response, Result result) {
		// find the file by id
		final long fileId = Long.parseLong(result.fileIdStr);
		File file = fileRMapper.selectEnabledById(fileId);
		if (file == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return newStatusNgResult();
		}
		// check the production
		Production production = productionRMapper.selectEnabledById(file.getProduction().getId());
		if (production == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return newStatusNgResult();
		}
		// check the file service group
		final long fileServiceGroupId = file.getFileServiceGroup().getId();
		logger.info("file service group id: " + fileServiceGroupId);
		FileServiceGroup fsg = fileServiceGroupRMapper.selectEnabledById(fileServiceGroupId);
		logger.info("file service group: " + JsonTool.toJson(fsg));
		if (fsg == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return newStatusNgResult();
		}
		// check the file service
		FileService params = new FileService();
		params.getGroup().setId(fsg.getId());
		params.setHost(result.webServerHost);
		FileService fileService = fileServiceRMapper.selectEnabledByGroupIdAndHost(params);
		logger.info("file service: " + JsonTool.toJson(fileService));
		if (fileService == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return newStatusNgResult();
		}
		
		// success
		result.ok = true;
		result.file = file;
		result.production = production;
		return result;
	}

	void recordHistory(HttpServletRequest request, DownloadTask task, Result result) {
		try {
			if (appConfig.getDisableDownloadHistory()) {
				logger.info("download history disabled");
				return;
			}
			DownloadHistory history = new DownloadHistory();
			history.reset();
			history.setTaskId(task.getId());
			history.setClientIp(result.clientIp);
			history.setWebServerHost(result.webServerHost);
			history.setRequestRoute(result.requestRoute);
			history.setRequestParameters(JsonTool.toJson(request.getParameterMap()));
			historyWMapper.insert(history);
			logger.info("download history saved (" + JsonTool.toJson(history) + ")");
		} catch (Exception e) {
			logger.warn(EMPTY, e);
		}
	}
	
/*
	void downloadLocalFile(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(request.getRequestURI());
		logger.debug(request.getRequestURL().toString());
		try {
			String path = "var/gopher.jpg";
			// path 是指欲下载的文件的路径。
			java.io.File file = new java.io.File(path);
			logger.debug("file length: " + file.length());
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空 response
			response.reset();
			// 设置 response 的 Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
*/

}
