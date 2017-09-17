package com.perhab.napalm.implementations.optional;

import java.util.Optional;
import java.util.function.Supplier;

public class NullCheckWithOptional implements NullCheck<Optional> {
    @Override
    public boolean isNull(Supplier<Optional> supplier) {
        return !supplier.get().isPresent();
    }
}
