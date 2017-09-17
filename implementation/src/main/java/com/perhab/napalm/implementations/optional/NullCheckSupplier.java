package com.perhab.napalm.implementations.optional;

import com.perhab.napalm.statement.ParameterSupplier;
import com.perhab.napalm.statement.StatementNotInitalizableException;

import java.util.Optional;
import java.util.function.Supplier;

public class NullCheckSupplier implements ParameterSupplier {
    public <T,R> T get(Class<T> type, Class<R> reciever) {

        if (reciever == RegularNullCheck.class) {
            return (T) new Supplier<Object>() {

                int i = 0;

                @Override
                public Object get() {
                    boolean shouldBeNull = i % 2 == 0;
                    if (shouldBeNull) {
                        return null;
                    } else {
                        return new Object();
                    }
                }
            };
        } else if (reciever == NullCheckWithOptional.class) {
            return (T) new Supplier<Optional>() {

                int i = 0;

                @Override
                public Optional get() {
                    boolean shouldBeNull = i % 2 == 0;
                    if (shouldBeNull) {
                        return Optional.empty();
                    } else {
                        return Optional.of(new Object());
                    }
                }
            };
        }
        throw new StatementNotInitalizableException("Cannot create supplier for type " + type + " and class " + reciever + ".");
    }
}
