package com.perhab.napalm.implementations.interfaceImplementation;

import java.util.concurrent.Callable;

public class Lambda implements InterfaceImplementation {
    @Override
    public Integer add(Integer a, Integer b) throws Exception {
        Callable<Integer> callable = () -> a + b;
        return callable.call();
    }
}
