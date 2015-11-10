package com.example.persist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.SdCardOrder;
import com.example.persist.must.SdCardOrderRMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class SdCardOrderRMapperTest {
	
	@Autowired
	private SdCardOrderRMapper mapper;

//	@Test
	public void testSelectAll() {
		List<SdCardOrder> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
