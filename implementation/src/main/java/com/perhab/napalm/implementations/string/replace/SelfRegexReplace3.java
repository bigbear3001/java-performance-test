package com.perhab.napalm.implementations.string.replace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelfRegexReplace3 implements CharacterReplace {

	Pattern p = Pattern.compile("a");
	
	@Override
	public String replace(final String string) {
		Matcher m = p.matcher(string);
		StringBuilder result = new StringBuilder();
		int lastIndex = 0;
		while (m.find()) {
			result.append(string.substring(lastIndex, m.start()));
			result.append('b');
			lastIndex = m.end();
		}
		result.append(string.substring(lastIndex));
		
		return result.toString();
	}

}
