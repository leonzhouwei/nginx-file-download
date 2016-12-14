package com.example.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.common.HttpResponseTool;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	public void handle(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.warn("", e);
		String message = e.getMessage();
		HttpResponseTool.writeInternalServerError(response, null, message, null);
	}

}
