package com.example.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.Version;

public final class HttpServletResponseUtil {

	static final String EMPTY = "";

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
			String json = responseDto.toJson();
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
			HttpStatus status, ServerError error) {
		try {
			response.setStatus(status.value());
			ResponseDto<ServerError> responseDto = new ResponseDto<ServerError>();
			responseDto.setInnerVersion(Version.VERSION);
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
			ServerError error) {
		writeError(response, HttpStatus.INTERNAL_SERVER_ERROR, error);
	}

	public static void writeInternalServerError(HttpServletResponse response,
			String id, String message, String url) {
		ServerError error = new ServerError();
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

}
