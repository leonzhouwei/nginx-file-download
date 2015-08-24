package com.example.persist.rmapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Account;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class AccountRMapperTest {
	
	@Autowired
	private AccountRMapper mapper;

	public void testSelectAll() {
		List<Account> c = mapper.selectAll();
		for (Account e : c) {
			System.out.println(e.getId() + ", " + e.getName());
		}
	}

}
