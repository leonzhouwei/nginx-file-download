package com.example.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.example.consts.HttpDefine;

public final class HttpResponseTool {

	static final String EMPTY = "";

	static final String CSS_CONTENT_TYPE = "text/css";
	static final String JAVASCRIPT_CONTENT_TYPE = "text/javascript";
	static final String IMAGE_CONTENT_TYPE = "image";

	private static final int BUFFER_SIZE = 1024;

	private static final Logger logger = LoggerFactory.getLogger(HttpResponseTool.class);

	private HttpResponseTool() {
	}

	public static void setDefaultContentType(HttpServletResponse response) {
		response.setContentType(HttpDefine.CONTENT_TYPE_VALUE_APPJSON_AND_UTF8);
	}

	public static <T> void writeResponse(HttpServletResponse response, ResponseDto<T> responseDto) {
		try {
			String json = JsonTool.toJson(responseDto);
			setDefaultContentType(response);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			logger.warn(StringUtils.EMPTY, e);
		}
	}

	public static <T> void writeResponse(HttpServletResponse response, T t) {
		ResponseDto<T> responseDto = new ResponseDto<>();
		responseDto.setContent(t);
		writeResponse(response, responseDto);
	}

	public static String getTrimedParameter(HttpServletRequest request, String key) {
		String s = request.getParameter(key);
		if (s == null) {
			s = EMPTY;
		}
		s = s.trim();
		return s;
	}

	public static void writeError(HttpServletResponse response, HttpStatus status, ServerErrorDto error) {
		response.setStatus(status.value());
		ResponseDto<ServerErrorDto> responseDto = new ResponseDto<>();
		responseDto.setError(error);
		writeResponse(response, responseDto);
	}

	public static void writeInternalServerError(HttpServletResponse response, ServerErrorDto error) {
		writeError(response, HttpStatus.INTERNAL_SERVER_ERROR, error);
	}

	public static void writeInternalServerError(HttpServletResponse response, String id, String message, String url) {
		ServerErrorDto error = new ServerErrorDto(id, message, url);
		writeError(response, HttpStatus.INTERNAL_SERVER_ERROR, error);
	}

	public static void writeInternalServerError(HttpServletResponse response, Exception e) {
		writeInternalServerError(response, null, e.getMessage(), null);
	}

	public static void writeInternalServerError(HttpServletResponse response, String message) {
		writeInternalServerError(response, null, message, null);
	}

	public static void writeInternalServerError(HttpServletResponse response) {
		writeInternalServerError(response, null, HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
	}

	public static void writeImage(HttpServletResponse response, File file) throws IOException {
		writeFile(IMAGE_CONTENT_TYPE, response, file);
	}

	public static void setStatusAs200(HttpServletResponse response) {
		setDefaultContentType(response);

	}

	public static void setStatusAsNotFound(HttpServletResponse response) {
		setDefaultContentType(response);
		HttpStatus status = HttpStatus.NOT_FOUND;
		ServerErrorDto error = new ServerErrorDto(status.name());
		writeError(response, status, error);
	}

	public static void setStatusAsNotFound(HttpServletResponse response, String msg) {
		setDefaultContentType(response);
		HttpStatus status = HttpStatus.NOT_FOUND;
		ServerErrorDto error = new ServerErrorDto(msg);
		writeError(response, status, error);
	}

	public static void setStatusAsUnauthorized(HttpServletResponse response) {
		setDefaultContentType(response);
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ServerErrorDto error = new ServerErrorDto(status.name());
		writeError(response, status, error);
	}

	public static void writeFromInputStream(String contentType, HttpServletResponse response, InputStream is)
			throws IOException {
		ServletOutputStream gos = null;
		try {
			gos = response.getOutputStream();
			int count = -1;
			byte[] data = new byte[BUFFER_SIZE];
			while ((count = is.read(data, 0, BUFFER_SIZE)) != -1) {
				gos.write(data, 0, count);
			}
			gos.flush();
			response.setContentType(contentType);
		} finally {
			if (gos != null) {
				gos.close();
			}
		}
	}

	public static void writeFile(String contentType, HttpServletResponse response, File file) throws IOException {
		if (!file.exists()) {
			setStatusAsNotFound(response);
			return;
		}
		try (InputStream is = new FileInputStream(file)) {
			writeFromInputStream(contentType, response, is);
		}
	}

	public static void writeCss(HttpServletResponse response, File file) throws IOException {
		writeFile(CSS_CONTENT_TYPE, response, file);
	}

}
