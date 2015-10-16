package com.example.common;

import java.util.Collection;

public final class ConsoleTool {
	
	private ConsoleTool() {
	}
	
	public static <T> void printCollection(Collection<T> c) {
		for (T e : c) {
			String json = JsonTool.toJson(e);
			System.out.println(json);
		}
		System.out.println("count: " + c.size());
	}
	
	public static <T> void print(T t) {
		String json = JsonTool.toJson(t);
		System.out.println(json);
	}
	
	public static void timeByMillis(final long startMillis, final long endMillis) {
		final long deltaMillis = endMillis - startMillis;
		System.out.println("delta seconds: " + deltaMillis/1000);
		System.out.println(" delta millis: " + deltaMillis);
		System.out.println(" start millis: " + startMillis);
		System.out.println("   end millis: " + endMillis);
	}
	
	public static final String toStartMessage(String content) {
		return "===== " + content + " started =====";
	}

	public static final String toEndMessage(String content) {
		return "===== " + content + " ended   =====";
	}
	
}
