package com.example.persist.must;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.common.Sha2Encoder;
import com.example.domain.Account;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
//@ActiveProfiles("test")
public class AccountWMapperTest {

	private static final String CIPHER = Sha2Encoder.encode("test"); // 9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08

	@Autowired
	private AccountWMapper mapper;
	@Autowired
	private ApplicationContext appCtx;

	@Test
	public void testInsert() {
		String[] activeProfiles = appCtx.getEnvironment().getActiveProfiles();
		System.out.println("activeProfiles:");
		for (String e : activeProfiles) {
			System.out.println(e);
		}
		Account account = new Account();
		account.reset();
		account.setName("mysql");
		account.setPassword(CIPHER);
		account.resetRoleAsNormal();
		mapper.insert(account);
	}

}
