package com.example.common;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.common.collect.Lists;

public final class JsonTool {
	
	private static class FastJsonValueFilter implements ValueFilter {

		@Override
		public Object process(Object object, String name, Object value) {
			Object ret = value;
			if (value instanceof Date) {
				ret = DateTimeTool.toIso8601((Date) value);
			}
			return ret;
		}

	}

	private JsonTool() {
	}

	public static <T> String toJson(T t) {
		return JSON.toJSONString(t, new FastJsonValueFilter(),
				SerializerFeature.UseISO8601DateFormat);
	}

	public static <T> T parse(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	public static <T> List<T> parseAll(Collection<String> c, Class<T> clazz) {
		if (c == null) {
			return Lists.newArrayList();
		}
		List<T> ret = Lists.newArrayList();
		for (String e : c) {
			T t = JSON.parseObject(e, clazz);
			ret.add(t);
		}
		return ret;
	}

	public static <T> List<T> parseList(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}

}
