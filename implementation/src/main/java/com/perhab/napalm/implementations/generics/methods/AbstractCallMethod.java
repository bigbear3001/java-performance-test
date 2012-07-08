package com.perhab.napalm.implementations.generics.methods;

public abstract class AbstractCallMethod implements CallMethod {

	public String method(String parameter) {
		return "Something" + parameter;
	}

}
