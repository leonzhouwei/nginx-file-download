package com.example.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public final class DateTimeTool {
	
	private DateTimeTool() {
	}
	
	public static String toIso8601(Date date) {
		if (date == null) {
			return null;
		}
		DateTime dt = new DateTime(date);
		return dt.toString();
	}
	
	public static String toLocal(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}

}
