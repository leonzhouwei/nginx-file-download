package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class DownloadTaskServiceImplTest {

	@Autowired
	private DownloadTaskServiceImpl service;
	
//	@Test
	public void testUpdateTimeCostMillis() {
		long costMillis = service.updateTimeCostMillis(1L);
		System.out.println(costMillis / 1000 + "s");
	}

}
