package com.perhab.napalm.implementations.string.concat;

public class StringBufferImplementation2 implements StringConcatination {
	public String concat(String a, String b) {
		return new StringBuffer().append(a).append(b).toString();
	}
}
