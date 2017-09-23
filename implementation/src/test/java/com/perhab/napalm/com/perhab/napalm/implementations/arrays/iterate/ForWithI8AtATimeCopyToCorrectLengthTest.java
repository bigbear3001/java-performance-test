package com.perhab.napalm.com.perhab.napalm.implementations.arrays.iterate;

import com.perhab.napalm.implementations.arrays.iterate.ForWithI8AtATimeCopyToCorrectLength;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ForWithI8AtATimeCopyToCorrectLengthTest {
    @Test
    public void testArrayCopy() {
        ForWithI8AtATimeCopyToCorrectLength test = new ForWithI8AtATimeCopyToCorrectLength();
        assertEquals((Integer) 6, test.sum(new Integer[]{1, 2, 3}));
        assertEquals((Integer) 28, test.sum(new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals((Integer) 36, test.sum(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
