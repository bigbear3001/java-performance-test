package com.perhab.napalm;

import com.perhab.napalm.string.concat.StringConcatination;

public class Statement {

	private StringConcatination implementation;
	
	public Statement(StringConcatination implementation) {
		this.implementation = implementation;
	}

	public Result execute() {
		Result result = new Result(this);
		result.setResult(implementation.concat("a", "b"));
		for(int i = 0 ; i < 1000000; i++) {
			implementation.concat("a", "b");
		}
		return result.stop();
	}

	@Override
	public String toString() {
		return implementation.getClass().getName();
	}
}
