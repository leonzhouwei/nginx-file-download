package com.example.domain;

public class Account extends Base {

	private Long id;
	private String name;
	private String password;
	private Role role;

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
		return super.toString() + " Account [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role
				+ "]";
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

}
