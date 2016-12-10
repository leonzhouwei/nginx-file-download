package com.example.persist.must;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Account;
import com.example.persist.must.AccountRMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class AccountRMapperTest {
	
	@Autowired
	private AccountRMapper mapper;

//	@Test
	public void testSelectAllIngoreEnabled() {
		List<Account> c = mapper.selectAllIngoreEnabled();
		for (Account e : c) {
			System.out.println(e.getId() + ", " + e.getName());
		}
	}

}
