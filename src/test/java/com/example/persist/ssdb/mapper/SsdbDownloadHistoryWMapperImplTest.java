package com.example.persist.ssdb.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.DownloadHistory;
import com.example.util.JsonTool;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class SsdbDownloadHistoryWMapperImplTest {
	
	@Autowired
	private SsdbDownloadHistoryWMapperImpl mapper;

//	@Test
	public void insert() {
		DownloadHistory e = new DownloadHistory();
		e.reset();
		e.setTaskId(1L);
		mapper.insert(e);
	}
	
	public void testKeyFor() {
		DownloadHistory e = new DownloadHistory();
		e.reset();
		e.setTaskId(1L);
		String key = SsdbDownloadHistoryWMapperImpl.keyFor(e);
		System.out.println(key);
		System.out.println(JsonTool.toJson(e));
	}

}
