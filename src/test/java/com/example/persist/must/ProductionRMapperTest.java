package com.example.persist.must;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.Production;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class ProductionRMapperTest {
	
	@Autowired
	private ProductionRMapper mapper;

//	@Test
	public void testSelectById() {
		Production e = mapper.selectById(1);
		ConsoleTool.print(e);
	}
	
//	@Test
	public void testSelectAll() {
		List<Production> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
