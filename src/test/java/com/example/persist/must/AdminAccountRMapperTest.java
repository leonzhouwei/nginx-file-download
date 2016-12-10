package com.example.persist.must;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Account;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class AdminAccountRMapperTest {
	
	@Autowired
	private AdminAccountRMapper mapper;

//	@Test
	public void testSelectAllIngoreEnabled() {
		List<Account> c = mapper.selectAll();
		for (Account e : c) {
			System.out.println(e.getId() + ", " + e.getName());
		}
	}

}
