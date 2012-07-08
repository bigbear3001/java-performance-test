package com.perhab.napalm.implementations.generics.methods;

public class InterfaceMethodCall extends AbstractCallMethod {
	
	Object object;
	
	public InterfaceMethodCall() {
		object = new MyInnerMethodCall(); 
	}

	@Override
	public String callMethod(final String parameter) {
		if (object instanceof AbstractCallMethod) {
			return ((AbstractCallMethod) object).method(parameter);
		}
		return null;
	}
	
	private class MyInnerMethodCall extends AbstractCallMethod {

		@Override
		public String callMethod(final String parameter) {
			return null;
		}
		
	}

}
