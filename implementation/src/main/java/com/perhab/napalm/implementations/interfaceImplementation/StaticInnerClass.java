package com.perhab.napalm.implementations.interfaceImplementation;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

public class StaticInnerClass implements InterfaceImplementation {
    @Override
    public Integer add(Integer a, Integer b) throws Exception {
        return new MyCallable(a, b).call();
    }

    @RequiredArgsConstructor
    static class MyCallable implements Callable<Integer> {

        private final Integer a;

        private final Integer b;

        @Override
        public Integer call() throws Exception {
            return a + b;
        }
    }
}
