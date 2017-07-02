package com.perhab.napalm.implementations.string.match;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class RegexMatch implements Match {
    @Override
    public boolean matches(String value) {
        return value.matches("[0-9]+[.]{3}[0-9]+");
    }
}
