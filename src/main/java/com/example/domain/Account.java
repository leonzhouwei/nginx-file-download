package com.example.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Account extends Base {

	private Long id;
	private String name;
	
	@JSONField(serialize=false)
	private transient String password;
	
	private Role role;
	private String localeLanguage;
	private String localeCountry;

	public static boolean isAdmin(Account account) {
		return Role.isAdmin(account);
	}

	public static boolean isValidAccount(Account account) {
		if (account == null || !account.getEnabled()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + " Account [id=" + id + ", name=" + name + ", role=" + role
				+ ", localeLanguage=" + localeLanguage + ", localeCountry=" + localeCountry + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void resetRoleAsNormal() {
		this.role.resetRoleAsNormal();
	}

	public void reset() {
		super.reset();
		setRole(new Role());
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLocaleLanguage() {
		return localeLanguage;
	}

	public void setLocaleLanguage(String localeLanguage) {
		this.localeLanguage = localeLanguage;
	}

	public String getLocaleCountry() {
		return localeCountry;
	}

	public void setLocaleCountry(String localeCountry) {
		this.localeCountry = localeCountry;
	}

}
