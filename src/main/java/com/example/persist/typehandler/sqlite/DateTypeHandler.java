package com.example.persist.typehandler.sqlite;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

public class DateTypeHandler extends BaseTypeHandler<Date> {
	
	public static void main(String[] args) {
		DateTime dt = new DateTime();
		String utc = dt.toString();
		System.out.println(utc);
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
			throws SQLException {
		DateTime dt = new DateTime(parameter);
		String utc = dt.toString();
		ps.setString(i, utc);
	}

	@Override
	public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return new DateTime(rs.getString(columnName)).toDate();
	}

	@Override
	public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return new DateTime(rs.getString(columnIndex)).toDate();
	}

	@Override
	public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return new DateTime(cs.getString(columnIndex)).toDate();
	}

}
