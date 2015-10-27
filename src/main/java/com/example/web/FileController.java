package com.example.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletResponseUtil;
import com.example.domain.File;
import com.example.dto.FileDto;
import com.example.persist.mapper.FileRMapper;

@RestController
public class FileController {

	@Autowired
	private FileRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_FILES, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<File> list = rMapper.selectAll();
		List<FileDto> dtos = FileDto.toList(list);
		HttpServletResponseUtil.writeResponse(response, dtos);
	}

	@RequestMapping(value = RouteDefine.API_FILES + "/{id}", method = RequestMethod.GET)
	public void getById(HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response) {
		File file = rMapper.selectById(Long.parseLong(id));
		HttpServletResponseUtil.writeResponse(response, file);
	}

}
