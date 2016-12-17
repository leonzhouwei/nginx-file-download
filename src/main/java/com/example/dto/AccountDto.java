package com.example.dto;

import java.util.Collection;
import java.util.List;

import com.example.domain.Account;
import com.google.common.collect.Lists;

public class AccountDto extends Base {
	
	public Long id;
	public String name;
	public Long roleId;
	
	public static List<AccountDto> toList(Collection<Account> c) {
		List<AccountDto> ret = Lists.newArrayList();
		for (Account e : c) {
			AccountDto dto = new AccountDto(e);
			ret.add(dto);
		}
		return ret;
	}
	
	public AccountDto() {
		super();
	}
	
	public AccountDto(Account e) {
		super(e);
		this.id = e.getId();
		this.name = e.getName();
		this.roleId = e.getRoleId();
	}

}
