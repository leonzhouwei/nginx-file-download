package com.example.common;

import org.junit.Assert;
import org.junit.Test;

public class FilePathToolTest {

	@Test
	public void testJoin_1() {
		final String expected = "/a";
		final String actual = FilePathTool.join("/", "a");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testJoin_2() {
		final String expected = "/a";
		final String actual = FilePathTool.join("/", "/a");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testJoin_3() {
		final String expected = "/a";
		final String actual = FilePathTool.join("/a");
		Assert.assertEquals(expected, actual);
	}

}
