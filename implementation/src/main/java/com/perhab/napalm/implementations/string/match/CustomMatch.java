package com.perhab.napalm.implementations.string.match;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class CustomMatch implements Match {
    @Override
    public boolean matches(String value) {
        int startsWithNumbers = 0;
        int containsDots = 0;
        for (char character : value.toCharArray()) {
            if (startsWithNumbers >= 0 && containsDots == 0 && character >= '0' && character <= '9') {
                startsWithNumbers++;
            } else if (startsWithNumbers == 0) {
                //we do not start with numbers
                return false;
            } else if (containsDots < 3 && character == '.') {
                containsDots++;
            } else if (containsDots == 0 || containsDots > 3 || character == '.') {
                //we didn't get exactly 3 dots
                return false;
            } else if (character >= '0' && character <= '9') {
                continue;
            } else {
                //didn't get just numbers at this
                return false;
            }
        }
        return true;
    }
}
