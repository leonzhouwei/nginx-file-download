package com.example.persist.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.App;
import com.example.domain.Production;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ProductionWMapperTest {
	
	@Autowired
	private ProductionWMapper mapper;

	@Test
	public void testInsert() {
		Production p = new Production();
		p.reset();
		p.setDescription("test");
		mapper.insert(p);
		System.out.println(p.getId());
	}

}
