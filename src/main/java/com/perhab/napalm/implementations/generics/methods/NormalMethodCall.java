package com.perhab.napalm.implementations.generics.methods;

public class NormalMethodCall extends AbstractCallMethod {

	@Override
	public String callMethod(final String parameter) {
		return method(parameter);
	}

}
