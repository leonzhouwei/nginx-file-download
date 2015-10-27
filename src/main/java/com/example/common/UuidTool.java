package com.example.common;

import java.util.UUID;

public final class UuidTool {

	private UuidTool() {
	}
	
	/**
	 * create a UUID in 8-4-4-4-12 format and in lowercase
	 * 
	 * @return
	 */
	public static String newUuid() {
		return UUID.randomUUID().toString().toLowerCase();
	}
	
}
