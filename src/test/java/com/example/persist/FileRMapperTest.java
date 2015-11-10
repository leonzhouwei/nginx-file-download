package com.example.persist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.File;
import com.example.persist.must.FileRMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class FileRMapperTest {
	
	@Autowired
	private FileRMapper mapper;
	
//	@Test
	public void testSelectAll() {
		List<File> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
