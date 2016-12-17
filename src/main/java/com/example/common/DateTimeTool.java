package com.example.common;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public final class DateTimeTool {

	private DateTimeTool() {
	}

	public static String toIso8601(Date date) {
		if (date == null) {
			return null;
		}
		DateTime dt = new DateTime(date, DateTimeZone.UTC);
		return dt.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

}
