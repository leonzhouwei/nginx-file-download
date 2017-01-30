package com.example.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.common.HttpResponseTool;
import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.webapi.RouteDefine;

public class AccountPrevillegeInterceptor extends BaseInterceptor {

	private AccountRMapper accountRMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
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
		if (uri.startsWith(RouteDefine.ADMIN) && !Account.isAdmin(account)) {
			HttpResponseTool.setStatusAsUnauthorized(response);
			return false;
		}
		return true;
	}

	public AccountRMapper getAccountRMapper() {
		return accountRMapper;
	}

	public void setAccountRMapper(AccountRMapper accountRMapper) {
		this.accountRMapper = accountRMapper;
	}

}
