package com.example.persist.nosql.ssdb.driver.impl;

class FieldValuePair<T> {
	
	private String field;
	private T value;
	
	public FieldValuePair() {
	}
	
	public FieldValuePair(String field) {
		this.field = field;
	}
	
	public FieldValuePair(String field, T value) {
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
