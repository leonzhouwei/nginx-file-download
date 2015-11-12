package com.example.persist.must;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.FileService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class FileServiceRMapperTest {
	
	@Autowired
	private FileServiceRMapper mapper;

//	@Test
	public void testSelectAll() {
		List<FileService> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
