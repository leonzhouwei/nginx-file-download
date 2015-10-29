package com.example.persist.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.File;
import com.example.persist.mapper.rdbms.FileWMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class FileWMapperTest {
	
	@Autowired
	private FileWMapper mapper;
	
//	@Test
	public void testInsert() {
		File e = new File();
		e.reset();
		e.setDir("test");
		e.setName("test");
		e.setSize(50L);
		mapper.insert(e);
	}

}
