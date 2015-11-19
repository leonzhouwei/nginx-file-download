package com.example.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MoneyToolTest {
	
	@Test
	public void testYuanToFen_1() {
		final String priceYuan = "1";
		final Long expected = 100L;
		final Long actual = MoneyTool.yuanToFen(priceYuan);
		assertEquals(expected, actual);
	}

	@Test
	public void testYuanToFen_2() {
		final String priceYuan = "1.23";
		final Long expected = 123L;
		final Long actual = MoneyTool.yuanToFen(priceYuan);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testYuanToFen_3() {
		final String priceYuan = "0.23";
		final Long expected = 23L;
		final Long actual = MoneyTool.yuanToFen(priceYuan);
		assertEquals(expected, actual);
	}

}
