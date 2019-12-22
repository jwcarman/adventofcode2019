package com.callibrity.adventofcode.intcode.io;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class BlockingQueueOutputConsumer implements Consumer<Long> {
    private final BlockingQueue<Long> queue;

    public BlockingQueueOutputConsumer() {
        this(new LinkedBlockingDeque<>());
    }

    @Override
    public void accept(Long value) {
        Uninterruptibles.putUninterruptibly(queue, value);
    }
}
