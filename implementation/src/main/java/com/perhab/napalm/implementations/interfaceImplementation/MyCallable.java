package com.perhab.napalm.implementations.interfaceImplementation;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class MyCallable implements Callable<Integer> {

    private final Integer a;

    private final Integer b;

    @Override
    public Integer call() throws Exception {
        return a + b;
    }
}
