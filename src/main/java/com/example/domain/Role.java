package com.example.domain;

public class Role {

	static final long ADMIN = 1L;
	static final long NORMAL = 2L;

	private Long id;
	
	public static boolean isAdmin(Account account) {
		return account.getRole().getId() == ADMIN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id == ADMIN) {
			this.id = ADMIN;
		} else {
			this.id = NORMAL;
		}
	}

	public void resetRoleAsNormal() {
		this.id = NORMAL;
	}

}
