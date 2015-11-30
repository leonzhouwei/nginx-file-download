package com.example.webapi.admin;

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
import com.example.common.JsonTool;
import com.example.domain.Production;
import com.example.persist.must.ProductionRMapper;
import com.example.persist.must.ProductionWMapper;
import com.example.webapi.RouteDefine;

@RestController
public class AdminProductionApi {

	static final Logger logger = LoggerFactory
			.getLogger(AdminProductionApi.class);

	@Autowired
	private ProductionRMapper rMapper;
	@Autowired
	private ProductionWMapper wMapper;

	@RequestMapping(value = RouteDefine.API_ADMIN_PRODUCTIONS, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<Production> list = rMapper.selectAllIgnoreEnabled();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_PRODUCTIONS + "/{id}", method = RequestMethod.GET)
	public void getById(HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response) {
		Production e = rMapper.selectById(Long.parseLong(id));
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.API_ADMIN_PRODUCTIONS + "/{id}/disable", method = RequestMethod.POST)
	public void disable(HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response) {
		Production e = rMapper.selectById(Long.parseLong(id));
		logger.debug(JsonTool.toJson(e));
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}
	
	@RequestMapping(value = RouteDefine.API_ADMIN_PRODUCTIONS + "/{id}/enable", method = RequestMethod.POST)
	public void enable(HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response) {
		Production e = rMapper.selectByIdIgnoreEnabled(Long.parseLong(id));
		logger.debug(JsonTool.toJson(e));
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
