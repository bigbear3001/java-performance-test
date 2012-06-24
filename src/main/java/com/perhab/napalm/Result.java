package com.perhab.napalm;

import lombok.Getter;

import com.perhab.napalm.statement.Statement;

public class Result {

	@Getter
	private Statement statement;
	
	private Object result;
	
	private long start;
	
	private long end;
	
	public Result(Statement statement) {
		this.statement = statement;
		start = System.currentTimeMillis();
	}

	public Result setResult(Object value) {
		this.result = value;
		return this;
	}

	public Result stop() {
		end = System.currentTimeMillis();
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Result) {
			return result.equals(((Result) obj).result);
		}
		return super.equals(obj);
	}

	public long getTime() {
		return end - start;
	}
	
	@Override
	public final String toString() {
		return statement.toString() + "(" + statement.getArguments() + ")";
	}

}
