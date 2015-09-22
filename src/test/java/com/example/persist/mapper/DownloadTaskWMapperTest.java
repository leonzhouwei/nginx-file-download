package com.example.persist.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.DownloadTask;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class DownloadTaskWMapperTest {
	
	@Autowired
	private DownloadTaskWMapper mapper;
	
//	@Test
	public void testInsert() {
		DownloadTask e = new DownloadTask();
		e.reset();
		e.setProductionId(6L);
		e.setFileDir("1");
		e.setFileName("test");
		e.setFileSize(50L);
		e.setClientIp("42.42.42.42");
		mapper.insert(e);
	}

}
