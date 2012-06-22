package com.perhab.napalm.string.concat;

public class StringBufferImplementation implements StringConcatination {
	public String concat(String a, String b) {
		return new StringBuffer().append(a).append(b).toString();
	}
}
