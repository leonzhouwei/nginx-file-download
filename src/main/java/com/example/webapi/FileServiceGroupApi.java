package com.example.webapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletResponseUtil;
import com.example.domain.FileServiceGroup;
import com.example.persist.must.FileServiceGroupRMapper;

@RestController
public class FileServiceGroupApi {

	@Autowired
	private FileServiceGroupRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_FILE_SERVICE_GROUPS, method = RequestMethod.GET)
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List<FileServiceGroup> list = rMapper.selectAll();
		HttpServletResponseUtil.writeResponse(response, list);
	}

}
