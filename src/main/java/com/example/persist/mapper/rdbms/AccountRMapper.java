package com.example.persist.mapper.rdbms;

import java.util.List;

import com.example.domain.Account;

public interface AccountRMapper {
	
	public List<Account> selectAll();
	
	public Account selectById(long id);
	
	public Account selectByNameAndPassword(Account e);

}
