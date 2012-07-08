package com.perhab.napalm;

import lombok.Getter;
import lombok.Setter;

import com.perhab.napalm.statement.BaseStatement;

public class Result {

	@Getter
	private BaseStatement statement;
	
	@Setter
	private Object result;
	
	@Getter
	private long[] times;
	
	private int timesSet = 0;
	
	public Result(BaseStatement statement) {
		this.statement = statement;
		times = new long[statement.getExpectedTimes()];
		time();
	}

	public void stop() {
		//time();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Result) {
			return result.equals(((Result) obj).result);
		}
		return super.equals(obj);
	}

	public long getTime() {
		return times[timesSet - 1] - times[0];
	}
	
	@Override
	public final String toString() {
		return statement.toString() + "(" + statement.getArguments() + ")";
	}

	public void time() {
		times[timesSet++] = System.currentTimeMillis();
	}

}