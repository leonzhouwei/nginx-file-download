package com.example.persist.must;

import com.example.domain.Account;

public interface AccountWMapper {

	public void insert(Account account);

	public void update(Account account);

	public void enable(Account account);
	
	public void disable(Account account);

	public void updatePassword(Account account);

}
