package com.example.webgui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Account;
import com.example.filter.LoginInterceptor;
import com.example.persist.mapper.rdbms.AccountRMapper;

@Controller
public class WebGuiIndexController {
	
	static final String INDEX = "index";
	static final String ADMIN_INDEX = "admin/" + INDEX;
	
	@Autowired
	private AccountRMapper accoutRMapper;

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Long id = LoginInterceptor.getAccountId(request);
		Account account = accoutRMapper.selectById(id);
		if (account.getIsAdmin()) {
			return ADMIN_INDEX;
		}
		return INDEX;
	}

}
