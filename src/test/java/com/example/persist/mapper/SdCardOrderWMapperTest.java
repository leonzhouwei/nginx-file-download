package com.example.persist.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.SdCardOrder;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class SdCardOrderWMapperTest {
	
	@Autowired
	private SdCardOrderWMapper mapper;

//	@Test
	public void testInsert() {
		SdCardOrder e = new SdCardOrder();
		e.reset();
		e.setFileId(1L);
		e.setPriceFen(30*100L);
		mapper.insert(e);
		System.out.println(e.getId());
	}

}
