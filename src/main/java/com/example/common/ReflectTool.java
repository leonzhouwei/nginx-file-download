package com.example.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.nutz.lang.Strings;

import com.google.common.collect.Maps;

public final class ReflectTool {

	static final String GET = "get";
	static final String GET_CLASS = GET + "Class";

	private ReflectTool() {
	}

	public static <T> Map<String, Object> toMap(T t) {
		Map<String, Object> ret = Maps.newHashMap();
		try {
			Field[] fields = t.getClass().getFields();
			for (Field f : fields) {
				String name = f.getName();
				Object value = f.get(t);
				if (value instanceof Date) {
					ret.put(name, DateTimeTool.toIso8601((Date) value));
				} else {
					ret.put(name, value);
				}
			}
			Method[] methods = t.getClass().getMethods();
			for (Method e : methods) {
				String methodName = e.getName();
				if (!methodName.startsWith(GET) || methodName.compareTo(GET_CLASS) == 0) {
					continue;
				}
				String name = Strings.lowerFirst(methodName.substring(GET.length()));
				Object value = e.invoke(t);
				if (value instanceof Date) {
					ret.put(name, DateTimeTool.toIso8601((Date) value));
				} else {
					ret.put(name, value);
				}
			}
		} catch (Exception e) {
			// no operations
		}
		return ret;
	}

}
