package com.example.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.example.common.ServerErrorDto;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public String handle(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		logger.warn("", e);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		String message = e.getMessage();
		ServerErrorDto error = new ServerErrorDto();
		error.setMessage(message);
		String json = JSON.toJSONString(error);
		return json;
	}

}
