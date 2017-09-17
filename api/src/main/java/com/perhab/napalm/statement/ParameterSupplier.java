package com.perhab.napalm.statement;

public interface ParameterSupplier {
    <T, R> T get(Class<T> parameterType, Class<R> reciever);
}
