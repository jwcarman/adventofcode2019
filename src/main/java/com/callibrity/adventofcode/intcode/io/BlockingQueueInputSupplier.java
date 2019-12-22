package com.callibrity.adventofcode.intcode.io;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class BlockingQueueInputSupplier implements Supplier<Long> {
    private final BlockingQueue<Long> queue;

    public BlockingQueueInputSupplier() {
        this(new LinkedBlockingDeque<>());
    }

    @Override
    public Long get() {
        return Uninterruptibles.takeUninterruptibly(queue);
    }
}
