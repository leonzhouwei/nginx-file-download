package com.example.persist.must;

import java.util.List;

import com.example.domain.Account;

public interface AccountRMapper {
	
	public List<Account> selectAllIngoreEnabled();
	
	public Account selectById(long id);
	
	public Account selectByIdIgnoreEnabled(long id);
	
	public Account selectByNameAndPassword(Account e);

}
