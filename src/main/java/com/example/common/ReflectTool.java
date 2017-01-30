package com.example.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

public final class ReflectTool {

	static final String GET = "get";
	static final String GET_CLASS = GET + "Class";

	private static final Logger logger = LoggerFactory.getLogger(ReflectTool.class);

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
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.warn(StringUtils.EMPTY, e);
		}
		return ret;
	}

}
