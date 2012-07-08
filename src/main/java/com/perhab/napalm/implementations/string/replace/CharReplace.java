package com.perhab.napalm.implementations.string.replace;

public class CharReplace implements CharacterReplace {

	@Override
	public String replace(final String string) {
		return string.replace('a', 'b');
	}

}
