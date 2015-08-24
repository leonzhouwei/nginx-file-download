package com.example.webgui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebGuiDownloadController {

	private static final Logger logger = LoggerFactory
			.getLogger(WebGuiDownloadController.class);

	@RequestMapping("/download/**")
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		String route = request.getRequestURI();
		logger.debug("route: " + route);
		logger.debug("Host: " + request.getParameter("Host"));
		logger.debug("X-Real-IP: " + request.getParameter("X-Real-IP"));
		logger.debug("X-Forwarded-For: " + request.getParameter("X-Forwarded-For"));
		//
		String fileRoute = route.replace("/download/", "");
		logger.debug("file route: " + fileRoute);
		String fileBaseName = fileRoute.substring(fileRoute.lastIndexOf("/") + 1,
				fileRoute.length());
		logger.debug("file base name: " + fileBaseName);
		response.setContentType("application/octet-stream");
		// 设置response的Header
		String xAccelRedirect = "/protected/" + fileRoute;
		logger.debug("X-Accel-Redirect: " + xAccelRedirect);
		response.setHeader("X-Accel-Redirect", xAccelRedirect);
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileBaseName);
	}

	public void get(HttpServletRequest request, HttpServletResponse response) {
		logger.debug(request.getRequestURI());
		logger.debug(request.getRequestURL().toString());
		try {
			String path = "var/gopher.jpg";
			// path是指欲下载的文件的路径。
			File file = new File(path);
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
