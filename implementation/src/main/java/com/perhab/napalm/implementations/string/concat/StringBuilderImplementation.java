package com.perhab.napalm.implementations.string.concat;

public class StringBuilderImplementation implements StringConcatination {
	public String concat(String a, String b) {
		return new java.lang.StringBuilder().append(a).append(b).toString();
	}
}
