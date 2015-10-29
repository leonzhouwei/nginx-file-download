package com.example.init;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.init.ssdb.SsdbRDriverBean;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class SsdbRDriverBeanTest {
	
	@Autowired
	private SsdbRDriverBean driver;

//	@Test
	public void test() {
		String value = driver.get("foo");
		System.out.println(value);
	}

}
