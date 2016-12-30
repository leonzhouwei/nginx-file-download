package com.example.persist.must;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.App;
import com.example.common.Sha2Encoder;
import com.example.domain.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AccountWMapperTest {
	
	private static final String TEST = "test_";
	private static final String CIPHER = Sha2Encoder.encode("test"); // 9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08
	
	@Autowired
	private AccountWMapper mapper;

	@Test
	public void testInsert() {
		final int start = 1001;
		final int end = 10001;
		for (int i = start; i < end; ++i) {
			Account account = new Account();
			account.reset();
			account.setName(TEST + i);
			account.setPassword(CIPHER);
			account.resetRoleAsNormal();
			mapper.insert(account);
		}
	}

}
