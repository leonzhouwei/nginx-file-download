package com.example.persist;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Production;
import com.example.persist.rdbms.ProductionWMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class ProductionWMapperTest {
	
	@Autowired
	private ProductionWMapper mapper;

//	@Test
	public void testInsert() {
		Production p = new Production();
		p.reset();
		p.setDescription("test");
		mapper.insert(p);
		System.out.println(p.getId());
	}

}
