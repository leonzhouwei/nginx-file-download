package com.example.web.interceptor;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;
import com.example.web.consts.RouteDefine;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	public static final String PREFIX = "example.com/";
	private static final String SESSION_ID = PREFIX + "sessionid";

	public static final String LOCALE = "locale";

	static final Set<String> unrestrictedRoutePatterns = Sets.newHashSet();

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	static {
		unrestrictedRoutePatterns.add(RouteDefine.BASE_ASSETS + ".*");
		unrestrictedRoutePatterns.add(RouteDefine.LOGIN + ".*");
		unrestrictedRoutePatterns.add(RouteDefine.API + ".*");
	}

	private AccountRMapper accountRMapper;

	public static void redirectToLogin(HttpServletResponse response) throws IOException {
		response.sendRedirect(RouteDefine.LOGIN);
	}

	public static void initSession(HttpServletRequest request, Account account) {
		if (sessionIdExist(request)) {
			destroySession(request);
		}
		logger.debug("account: " + account);
		request.getSession().setAttribute(SESSION_ID, account.getId());
		String country = account.getLocaleCountry();
		Locale locale;
		if (Strings.isNullOrEmpty(country)) {
			locale = new Locale(account.getLocaleLanguage());
		} else {
			locale = new Locale(account.getLocaleLanguage(), country);
		}
		request.getSession().setAttribute(LOCALE, locale.toString());
	}

	public static String getSessionLocale(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(LOCALE);
	}

	public static Long getSessionId(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(SESSION_ID);
		return (Long) obj;
	}

	public static boolean sessionIdExist(HttpServletRequest request) {
		Long sessionId = getSessionId(request);
		return sessionId != null;
	}

	public static void destroySession(HttpServletRequest request) {
		if (!sessionIdExist(request)) {
			return;
		}
		request.getSession().removeAttribute(SESSION_ID);
		request.getSession().removeAttribute(LOCALE);
	}

	public static Long getAccountId(HttpServletRequest request) {
		return getSessionId(request);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		for (String e : unrestrictedRoutePatterns) {
			if (uri.matches(e)) {
				return true;
			}
		}
		Long id = getAccountId(request);
		if (id == null) {
			redirectToLogin(response);
			return false;
		}
		Account account = accountRMapper.selectById(id);
		if (Account.isValidAccount(account)) {
			return true;
		}
		redirectToLogin(response);
		return false;
	}

	public AccountRMapper getAccountRMapper() {
		return accountRMapper;
	}

	public void setAccountRMapper(AccountRMapper accountRMapper) {
		this.accountRMapper = accountRMapper;
	}

}
