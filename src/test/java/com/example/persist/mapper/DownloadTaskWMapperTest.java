package com.example.persist.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.DownloadTask;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class DownloadTaskWMapperTest {
	
	@Autowired
	private DownloadTaskWMapper mapper;
	@Autowired
	private DownloadTaskRMapper rMapper;
	
//	@Test
	public void testUpdate() {
		DownloadTask e = rMapper.selectById(3L);
		e.resetLastDldededAt();
		e.resetTimeCostMillis();
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
