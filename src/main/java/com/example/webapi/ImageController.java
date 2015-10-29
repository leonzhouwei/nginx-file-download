package com.example.webapi;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletResponseUtil;
import com.example.config.AppConfig;

@RestController
public class ImageController {

	@Autowired
	private AppConfig appConfig;

	@RequestMapping(value = RouteDefine.ASSETS_IMAGES + "/**", method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String uri = request.getRequestURI();
		String imageFilePath = appConfig.getImageDirPath()
				+ uri.substring(RouteDefine.ASSETS_IMAGES.length());
		File file = new File(imageFilePath);
		HttpServletResponseUtil.writeImage(response, file);
	}

}
