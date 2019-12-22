package com.callibrity.adventofcode.intcode.io;

import java.util.PrimitiveIterator;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class ListInputSupplier implements Supplier<Long> {

    private final PrimitiveIterator.OfLong iterator;

    public ListInputSupplier(long... inputValues) {
        iterator = LongStream.of(inputValues).iterator();
    }

    @Override
    public Long get() {
        return iterator.next();
    }
}
