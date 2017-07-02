package com.perhab.napalm.implementations.string.match;

import java.util.regex.Pattern;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class RegexMatchPrecompiled implements Match {

    Pattern PATTERN = Pattern.compile("[0-9]+[.]{3}[0-9]+");

    @Override
    public boolean matches(String value) {
        return PATTERN.matcher(value).matches();
    }
}
