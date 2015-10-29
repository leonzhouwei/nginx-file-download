package com.example.persist.nosql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.DownloadHistory;
import com.example.persist.nosql.DownloadHistoryRMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class DownloadHistoryRMapperTest {
	
	@Autowired
	private DownloadHistoryRMapper mapper;

//	@Test
	public void testSelectAll() {
		List<DownloadHistory> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
