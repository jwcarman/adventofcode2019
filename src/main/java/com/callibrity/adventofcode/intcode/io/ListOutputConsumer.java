package com.callibrity.adventofcode.intcode.io;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Getter
public class ListOutputConsumer implements Consumer<Long> {

    private final List<Long> values;

    public ListOutputConsumer() {
        this(new LinkedList<>());
    }

    @Override
    public void accept(Long value) {
        values.add(value);
    }
}
