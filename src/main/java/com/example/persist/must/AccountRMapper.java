package com.example.persist.must;

import java.util.List;

import com.example.domain.Account;

public interface AccountRMapper {
	
	public Account selectById(long id);
	
	public Account selectEnabledById(long id);
	
	public Account selectEnabledByNameAndPassword(Account e);
	
	public List<Account> selectAll();
	
	public List<Account> selectAllEnabled();

}
