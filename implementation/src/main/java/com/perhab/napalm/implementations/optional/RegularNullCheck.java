package com.perhab.napalm.implementations.optional;

import java.util.function.Supplier;

public class RegularNullCheck implements NullCheck<Object> {
    @Override
    public boolean isNull(Supplier<Object> supplier) {
        return supplier.get() == null;
    }
}
