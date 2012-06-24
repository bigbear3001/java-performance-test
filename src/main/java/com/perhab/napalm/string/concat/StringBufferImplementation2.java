package com.perhab.napalm.string.concat;

public class StringBufferImplementation2 implements StringConcatination {
	public String concat(String a, String b) {
		return new StringBuffer().append(a).append(b).toString();
	}
}
