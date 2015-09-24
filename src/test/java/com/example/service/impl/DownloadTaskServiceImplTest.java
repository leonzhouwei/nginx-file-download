package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.impl.DownloadTaskServiceImpl;

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
