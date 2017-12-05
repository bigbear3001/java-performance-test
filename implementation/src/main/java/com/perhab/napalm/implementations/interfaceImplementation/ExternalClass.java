package com.perhab.napalm.implementations.interfaceImplementation;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;

public class ExternalClass implements InterfaceImplementation {
    @Override
    public Integer add(Integer a, Integer b) throws Exception {
        return new MyCallable(a, b).call();
    }
}
