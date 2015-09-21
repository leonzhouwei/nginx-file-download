package com.example.persist.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.App;
import com.example.domain.Account;
import com.example.persist.mapper.AccountRMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AccountRMapperTest {
	
	@Autowired
	private AccountRMapper mapper;

	@Test
	public void testSelectAll() {
		List<Account> c = mapper.selectAll();
		for (Account e : c) {
			System.out.println(e.getId() + ", " + e.getName());
		}
	}

}
