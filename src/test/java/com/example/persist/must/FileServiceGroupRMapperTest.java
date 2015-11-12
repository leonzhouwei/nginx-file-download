package com.example.persist.must;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.FileServiceGroup;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class FileServiceGroupRMapperTest {
	
	@Autowired
	private FileServiceGroupRMapper mapper;

//	@Test
	public void testSelectAll() {
		List<FileServiceGroup> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
