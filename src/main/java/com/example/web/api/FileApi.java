package com.example.web.api;

import java.util.List;

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
import com.example.web.consts.RouteDefine;

@RestController
public class FileApi {

	static final Logger logger = LoggerFactory.getLogger(FileApi.class);

	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.API + "/files", method = RequestMethod.GET)
	public void getAll(HttpServletResponse response) {
		List<File> list = rMapper.selectAllEnabled();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API + "/files/{id}", method = RequestMethod.GET)
	public void getById(@PathVariable Long id, HttpServletResponse response) {
		File file = rMapper.selectEnabledById(id);
		HttpResponseTool.writeResponse(response, file);
	}

}
