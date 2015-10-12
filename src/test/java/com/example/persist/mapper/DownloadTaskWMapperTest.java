package com.example.persist.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.App;
import com.example.domain.DownloadTask;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class DownloadTaskWMapperTest {
	
	@Autowired
	private DownloadTaskWMapper mapper;
	
	@Test
	public void testUpdate() {
		DownloadTask e = new DownloadTask();
		e.setId(3L);
		e.resetLastDldededAt();
		e.resetUpdatedAt();
		mapper.updateLastDldedAt(e);
	}
	
	public void testInsert() {
		DownloadTask e = new DownloadTask();
		e.reset();
		e.setProductionId(6L);
		e.setFileId(1L);
		e.setClientIp("42.42.42.42");
		mapper.insert(e);
	}

}
