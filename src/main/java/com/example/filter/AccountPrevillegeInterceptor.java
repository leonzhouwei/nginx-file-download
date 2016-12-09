package com.example.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.HttpResponseTool;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.webapi.RouteDefine;

public class AccountPrevillegeInterceptor implements HandlerInterceptor {
	
	private AccountRMapper accountRMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		for (String e : LoginInterceptor.unrestrictedRoutePatterns) {
			if (uri.matches(e)) {
				return true;
			}
		}
		Long id = LoginInterceptor.getAccountId(request);
		if (id == null) {
			return false;
		} 
		Account account = accountRMapper.selectById(id);
		if (!Account.isValidAccount(account)) {
			return false;
		}
		if (uri.startsWith(RouteDefine.ADMIN)) {
			if (!account.isAdmin()) {
				HttpResponseTool.setStatusAsUnauthorized(response);
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// no operations
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// no operations
	}

	public AccountRMapper getAccountRMapper() {
		return accountRMapper;
	}

	public void setAccountRMapper(AccountRMapper accountRMapper) {
		this.accountRMapper = accountRMapper;
	}

}
