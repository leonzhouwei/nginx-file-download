package com.example.webapi.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpResponseTool;
import com.example.domain.FileService;
import com.example.persist.must.FileServiceRMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminFileServiceApi {

	@Autowired
	private FileServiceRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICES, method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List<FileService> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

}
