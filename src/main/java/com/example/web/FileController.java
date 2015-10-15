package com.example.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.File;
import com.example.util.HttpServletResponseUtil;
import com.google.common.collect.Lists;

@RestController
public class FileController {

	@RequestMapping(value = RouteDefine.API_FILES, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<File> list = Lists.newArrayList();
		File e = new File();
		e.reset();
		e.setId(1L);
		e.setDir("/home/test/data");
		e.setName("lucy.rmvb");
		list.add(e);
		HttpServletResponseUtil.writeJson(response, list);
	}

}
