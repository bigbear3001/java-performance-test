package com.perhab.napalm.implementations.string.cut;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class RegexCut implements Cut {
    @Override
    public String cut(String value) {
        return value.replaceAll("^\\[L(.*?);$", "$1");
    }
}
