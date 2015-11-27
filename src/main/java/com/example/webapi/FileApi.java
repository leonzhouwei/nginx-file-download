package com.example.webapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.File;
import com.example.persist.must.FileRMapper;

@RestController
public class FileApi {

	static final Logger logger = LoggerFactory.getLogger(FileApi.class);

	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_FILES, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<File> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_FILES + "/{id}", method = RequestMethod.GET)
	public void getById(HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response) {
		File file = rMapper.selectById(Long.parseLong(id));
		HttpResponseTool.writeResponse(response, file);
	}

}
