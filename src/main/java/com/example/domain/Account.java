package com.example.domain;

public class Account extends Base {

	static final long ROLE_ADMIN = 1L;
	static final long ROLE_NORMAL = 2L;

	private Long id;
	private String name;
	private String password;
	private Long roleId;

	public static boolean isValidAccount(Account account) {
		if (account == null || !account.getEnabled()) {
			return false;
		}
		return true;
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		if (roleId == ROLE_ADMIN) {
			this.roleId = roleId;
		} else {
			this.roleId = ROLE_NORMAL;
		}
	}

	public boolean isAdmin() {
		return roleId == ROLE_ADMIN;
	}

}
