package com.example.persist.must;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Production;
import com.example.persist.must.AdminProductionWMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class ProductionWMapperTest {
	
	@Autowired
	private AdminProductionWMapper mapper;

//	@Test
	public void testInsert() {
		Production p = new Production();
		p.reset();
		p.setDescription("test");
		mapper.insert(p);
		System.out.println(p.getId());
	}

}
