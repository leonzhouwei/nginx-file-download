package com.example.web.api.admin;

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
import com.example.common.JsonTool;
import com.example.domain.Production;
import com.example.persist.must.ProductionRMapper;
import com.example.persist.must.ProductionWMapper;
import com.example.web.consts.RouteDefine;

@RestController
public class AdminProductionApi {

	static final Logger logger = LoggerFactory.getLogger(AdminProductionApi.class);

	@Autowired
	private ProductionRMapper rMapper;
	@Autowired
	private ProductionWMapper wMapper;

	@RequestMapping(value = RouteDefine.ADMIN_API + "/productions", method = RequestMethod.GET)
	public void getAll(HttpServletResponse response) {
		List<Production> list = rMapper.selectAll();
		HttpResponseTool.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.ADMIN_API + "/productions/{id}", method = RequestMethod.GET)
	public void getById(HttpServletResponse response, @PathVariable Long id) {
		Production e = rMapper.selectById(id);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.ADMIN_API + "/productions/{id}/actions/disable", method = RequestMethod.POST)
	public void disable(HttpServletResponse response, @PathVariable Long id) {
		Production e = rMapper.selectById(id);
		logger.debug(JsonTool.toJson(e));
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.disable();
		wMapper.disable(e);
		HttpResponseTool.writeResponse(response, e);
	}

	@RequestMapping(value = RouteDefine.ADMIN_API + "/productions/{id}/actions/enable", method = RequestMethod.POST)
	public void enable(HttpServletResponse response, @PathVariable Long id) {
		Production e = rMapper.selectById(id);
		logger.debug(JsonTool.toJson(e));
		if (e == null) {
			HttpResponseTool.setStatusAsNotFound(response);
			return;
		}
		e.enable();
		wMapper.enable(e);
		HttpResponseTool.writeResponse(response, e);
	}

}
