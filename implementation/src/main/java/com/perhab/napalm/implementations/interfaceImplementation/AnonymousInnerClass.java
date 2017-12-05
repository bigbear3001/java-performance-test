package com.perhab.napalm.implementations.interfaceImplementation;

import java.util.concurrent.Callable;

public class AnonymousInnerClass implements InterfaceImplementation {
    @Override
    public Integer add(Integer a, Integer b) throws Exception {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return a + b;
            }
        };
        return callable.call();
    }
}
