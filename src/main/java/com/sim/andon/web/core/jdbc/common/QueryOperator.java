package com.sim.andon.web.core.jdbc.common;

public class QueryOperator {
	
	private String field;
	
	private String op;
	
	private String value;
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
