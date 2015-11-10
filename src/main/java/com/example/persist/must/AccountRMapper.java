package com.example.persist.must;

import java.util.List;

import com.example.domain.Account;

public interface AccountRMapper {
	
	public List<Account> selectAll();
	
	public Account selectById(long id);
	
	public Account selectByNameAndPassword(Account e);

}
