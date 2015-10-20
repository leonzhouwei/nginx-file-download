package com.example.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public final class DateTimeTool {
	
	private DateTimeTool() {
	}
	
	public static String toIso8601(Date date) {
		DateTime dt = new DateTime(date);
		return dt.toString();
	}
	
	public static String toLocal(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}

}
