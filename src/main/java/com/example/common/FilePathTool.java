package com.example.common;

public final class FilePathTool {
	
	private FilePathTool() {
	}
	
	public static final String join(String... strings) {
		if (strings == null || strings.length == 0) {
			return null;
		}
		if (strings.length == 1) {
			return strings[0];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(strings[0]);
		String pre = strings[0];
		for (int i = 1; i < strings.length; ++i) {
			String e = strings[i];
			if (pre.endsWith("/") && e.startsWith("/")) {
				e = e.substring(1);
			}
			sb.append(e);
			pre = e;
		}
		return sb.toString();
	}
	
}
