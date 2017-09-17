package com.perhab.napalm.implementations.optional;

import com.perhab.napalm.statement.Execute;
import com.perhab.napalm.statement.Parameter;

import java.util.function.Supplier;

public interface NullCheck<T> {

    @Execute(parameters = @Parameter(supplier = NullCheckSupplier.class))
    boolean isNull(Supplier<T> suppplier);
}
