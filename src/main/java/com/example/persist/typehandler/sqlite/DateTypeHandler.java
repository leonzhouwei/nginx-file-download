package com.example.persist.typehandler.sqlite;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

public class DateTypeHandler extends BaseTypeHandler<Date> {
	
	public static void main(String[] args) {
		System.out.println(toString(new Date()));
	}

	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	static String toString(Date date) {
		DateTime dt = new DateTime(date, DateTimeZone.UTC);
		return dt.toString(PATTERN);
	}

	static Date fromString(String string) {
		return DateTimeFormat.forPattern(PATTERN).withZoneUTC().parseDateTime(string).toDate();
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, toString(parameter));
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return fromString(rs.getString(columnName));
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return fromString(rs.getString(columnIndex));
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return fromString(cs.getString(columnIndex));
	}

}
