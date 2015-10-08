package com.example.persist.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.App;
import com.example.domain.SdCardOrder;
import com.example.util.ConsoleTool;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class SdCardOrderRMapperTest {
	
	@Autowired
	private SdCardOrderRMapper mapper;

	@Test
	public void testSelectAll() {
		List<SdCardOrder> c = mapper.selectAll();
		ConsoleTool.printCollection(c);
	}

}
