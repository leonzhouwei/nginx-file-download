package com.example.persist.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.Production;
import com.example.persist.mapper.rdbms.ProductionRMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class ProductionRMapperTest {
	
	@Autowired
	private ProductionRMapper mapper;

//	@Test
	public void testSelectAll() {
		List<Production> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
