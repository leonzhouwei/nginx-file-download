package com.example.persist.must;

import java.util.List;

import com.example.domain.Account;

public interface AdminAccountRMapper {
	
	public List<Account> selectAll();
	
	public Account selectById(long id);
	
}
