package com.example.webapi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.HttpServletResponseUtil;
import com.example.domain.Production;
import com.example.persist.rdbms.ProductionRMapper;

@RestController
public class ProductionController {

	@Autowired
	private ProductionRMapper rMapper;

	@RequestMapping(value = RouteDefine.API_PRODUCTIONS, method = RequestMethod.GET)
	public void getAll(HttpServletRequest request, HttpServletResponse response) {
		List<Production> list = rMapper.selectAll();
		HttpServletResponseUtil.writeResponse(response, list);
	}

	@RequestMapping(value = RouteDefine.API_PRODUCTIONS + "/{id}", method = RequestMethod.GET)
	public void getById(HttpServletRequest request, @PathVariable String id,
			HttpServletResponse response) {
		Production e = rMapper.selectById(Long.parseLong(id));
		HttpServletResponseUtil.writeResponse(response, e);
	}

}
