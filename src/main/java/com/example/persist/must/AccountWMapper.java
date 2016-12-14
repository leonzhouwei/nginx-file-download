package com.example.persist.must;

import com.example.domain.Account;

public interface AccountWMapper {

	public void insert(Account account);

	public void update(Account account);

	public void updateEnabled(Account account);

	public void delete(Account account);

}
