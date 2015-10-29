package com.example.persist.nosql.ssdb.driver.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public final class SplitTool {
	
	private SplitTool() {}
	
	public static <T> List<List<T>> split(List<T> list, final int perMaxSize) {
		List<List<T>> result = Lists.newArrayList();
		final int size = list.size();
		if (size <= perMaxSize) {
			result.add(list);
		} else {
			int count = size / perMaxSize;
			if (size % perMaxSize != 0) {
				count += 1;
			}
			for (int i = 0; i < count; ++i) {
				List<T> e = Lists.newArrayList();
				result.add(e);
			}
			for (int i = 0; i < size; ++i) {
				final int index = i / perMaxSize;
				List<T> inner = result.get(index);
				inner.add(list.get(i));
			}
		}
		
		return result;
	}

	public static <T> List<Set<T>> split(Set<T> set, final int perMaxSize) {
		List<Set<T>> result = Lists.newArrayList();
		final int size = set.size();
		if (size <= perMaxSize) {
			result.add(set);
		} else {
			int count = size / perMaxSize;
			if (size % perMaxSize != 0) {
				count += 1;
			}
			for (int i = 0; i < count; ++i) {
				Set<T> e = Sets.newHashSet();
				result.add(e);
			}
			int i = 0;
			for (T e : set) {
				final int index = i / perMaxSize;
				Set<T> inner = result.get(index);
				inner.add(e);
				i += 1;
			}
		}
		
		return result;
	}
	
	public static <K, V> List<Map<K, V>> split(Map<K, V> map, final int perMaxSize) {
		List<Map<K, V>> result = Lists.newArrayList();
		final int size = map.size();
		if (size <= perMaxSize) {
			result.add(map);
		} else {
			int count = size / perMaxSize;
			if (size % perMaxSize != 0) {
				count += 1;
			}
			for (int i = 0; i < count; ++i) {
				Map<K, V> e = Maps.newHashMap();
				result.add(e);
			}
			int i = 0;
			Set<Entry<K, V>> set = map.entrySet();
			for (Entry<K, V> e : set) {
				final int index = i / perMaxSize;
				Map<K, V> inner = result.get(index);
				inner.put(e.getKey(), e.getValue());
				i += 1;
			}
		}
		
		return result;
	}
	
}
