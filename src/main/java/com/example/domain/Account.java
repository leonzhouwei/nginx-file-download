package com.example.domain;

public class Account extends Base {

	private Long id;
	private String name;
	private String password;
	private Integer isAdmin;

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

	public Boolean getIsAdmin() {
		return isAdmin == 1;
	}

	public void setIsAdmin(Boolean isAdmin) {
		if (isAdmin) {
			this.isAdmin = 1;
		} else {
			this.isAdmin = 0;
		}
	}

}
