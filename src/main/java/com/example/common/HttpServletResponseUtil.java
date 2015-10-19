package com.example.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public final class HttpServletResponseUtil {

	static final String EMPTY = "";

	private static final String IMAGE_CONTENT_TYPE = "image";
	private static final int bufferSize = 1024;

	private static final Logger logger = LoggerFactory
			.getLogger(HttpServletResponseUtil.class);

	private HttpServletResponseUtil() {
	}

	public static void setDefaultContentType(HttpServletResponse response) {
		response.setContentType(HttpDefine.CONTENT_TYPE_VALUE_APPJSON_AND_UTF8);
	}

	public static <T> void writeJson(HttpServletResponse response, T t) {
		try {
			String json = JSON.toJSONString(t,
					SerializerFeature.UseISO8601DateFormat);
			setDefaultContentType(response);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public static <T> void writeResponse(HttpServletResponse response,
			ResponseDto<T> responseDto) {
		try {
			String json = JSON.toJSONString(responseDto,
					SerializerFeature.UseISO8601DateFormat);
			setDefaultContentType(response);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public static <T> void writeResponse(HttpServletResponse response, T t) {
		try {
			ResponseDto<T> responseDto = new ResponseDto<T>();
			responseDto.setContent(t);
			writeResponse(response, responseDto);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public static String getTrimedParameter(HttpServletRequest request,
			String key) {
		String s = request.getParameter(key);
		if (s == null) {
			s = EMPTY;
		}
		s = s.trim();
		return s;
	}

	public static void writeError(HttpServletResponse response,
			HttpStatus status, ServerErrorDto error) {
		try {
			response.setStatus(status.value());
			ResponseDto<ServerErrorDto> responseDto = new ResponseDto<ServerErrorDto>();
			responseDto.setError(error);
			String json = JSON.toJSONString(responseDto,
					SerializerFeature.UseISO8601DateFormat);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.close();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	public static void writeInternalServerError(HttpServletResponse response,
			ServerErrorDto error) {
		writeError(response, HttpStatus.INTERNAL_SERVER_ERROR, error);
	}

	public static void writeInternalServerError(HttpServletResponse response,
			String id, String message, String url) {
		ServerErrorDto error = new ServerErrorDto();
		error.setMessage(message);
		writeError(response, HttpStatus.INTERNAL_SERVER_ERROR, error);
	}

	public static void writeInternalServerError(HttpServletResponse response,
			Exception e) {
		writeInternalServerError(response, null, e.getMessage(), null);
	}

	public static void writeInternalServerError(HttpServletResponse response,
			String message) {
		writeInternalServerError(response, null, message, null);
	}

	public static void writeInternalServerError(HttpServletResponse response) {
		writeInternalServerError(response, null,
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), null);
	}

	public static void writeImage(HttpServletResponse response, File imageFile)
			throws IOException {
		InputStream is = null;
		try {
			if (imageFile.exists() == false) {
				// image file does not exist
				setStatusAsNotFound(response);
				return;
			}
			is = new FileInputStream(imageFile);
			writeImage(response, is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public static void writeImage(HttpServletResponse response, InputStream is)
			throws IOException {
		ServletOutputStream gos = null;
		try {
			gos = response.getOutputStream();
			int count = -1;
			byte data[] = new byte[bufferSize];
			while ((count = is.read(data, 0, bufferSize)) != -1) {
				gos.write(data, 0, count);
			}
			gos.flush();
			response.setContentType(IMAGE_CONTENT_TYPE);
		} finally {
			if (gos != null) {
				gos.close();
			}
		}
	}

	public static void setStatusAsNotFound(HttpServletResponse response) {
		response.setStatus(HttpStatus.NOT_FOUND.value());
	}

}
