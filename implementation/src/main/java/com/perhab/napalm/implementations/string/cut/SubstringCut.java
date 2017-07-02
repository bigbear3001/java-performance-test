package com.perhab.napalm.implementations.string.cut;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class SubstringCut implements Cut {
    @Override
    public String cut(String value) {
        String frontCut = value.substring(2);
        return frontCut.substring(0, frontCut.length() - 1);
    }
}
