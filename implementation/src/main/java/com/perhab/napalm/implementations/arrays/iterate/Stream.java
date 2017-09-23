package com.perhab.napalm.implementations.arrays.iterate;

import java.util.Arrays;

/**
 * Created by bigbear3001 on 01.07.17.
 */
public class Stream implements ArrayIterationUnevenLength {

    @Override
    public Integer sum(final Integer[] integers) {
        return Arrays.stream(integers).mapToInt(Integer::intValue).sum();
    }
}
