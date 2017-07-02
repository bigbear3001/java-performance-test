package com.perhab.napalm.implementations.string.cut;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class SubstringSingleCut implements Cut {
    @Override
    public String cut(String value) {
        return value.substring(2, value.length() - 1);
    }
}
