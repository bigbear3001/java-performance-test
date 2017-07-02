package com.perhab.napalm.statement.arrayargument;

import com.perhab.napalm.statement.Parameter;

import java.util.Iterator;

/**
 * Created by bigbear3001 on 02.07.17.
 */
public class ArrayArgumentDefinition {

    private Integer first;
    private Integer last;

    public ArrayArgumentDefinition(Integer start, Integer end) {
        first = start;
        last = end;
    }

    public static ArrayArgumentDefinition parse(Parameter parameter) {
        int startsWithNumbers = 0;
        int containsDots = 0;
        String arrayDefinition = parameter.arrayDefinition();
        for (char character : arrayDefinition.toCharArray()) {
            if (startsWithNumbers >= 0 && containsDots == 0 && character >= '0' && character <= '9') {
                startsWithNumbers++;
            } else if (startsWithNumbers == 0) {
                throw new InvalidArrayArgumentDefinition("ArrayArgumentDefinition doesn't start with numbers.");
            } else if (containsDots < 3 && character == '.') {
                containsDots++;
            } else if (containsDots == 0 || containsDots > 3 || character == '.') {
                throw new InvalidArrayArgumentDefinition("ArrayArgumentDefinition didn't contain 3 dots in the middle.");
            } else if (character < '0' && character > '9') {
                throw new InvalidArrayArgumentDefinition("ArrayArgumentDefinition didn't end with numbers");
            }
        }
        return new ArrayArgumentDefinition(Integer.parseInt(arrayDefinition.substring(0, startsWithNumbers)), Integer.parseInt(arrayDefinition.substring(startsWithNumbers + containsDots)));
    }

    public Iterable<String> getValues() {
        return new ArrayArgumentDefinitionIterable();
    }

    public int getLength() {
        return last - first + 1;
    }

    private class ArrayArgumentDefinitionIterable implements Iterable<String> {
        public ArrayArgumentDefinitionIterable() {
        }

        @Override
        public Iterator<String> iterator() {
            return new ArrayArgumentDefinitionIterator();
        }
    }

    private class ArrayArgumentDefinitionIterator implements Iterator<String> {

        int current = first;

        @Override
        public boolean hasNext() {
            return current <= last;
        }

        @Override
        public String next() {
            return current++ + "";
        }
    }
}
