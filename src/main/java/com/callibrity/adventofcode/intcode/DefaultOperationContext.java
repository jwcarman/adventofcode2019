package com.callibrity.adventofcode.intcode;

import com.callibrity.adventofcode.intcode.ops.OperationContext;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class DefaultOperationContext implements OperationContext {

    private final IntCodeProgramState memory;
    private int parameterModes;
    private boolean complete = false;
    private final BlockingQueue<Integer> inputQueue;
    private final BlockingQueue<Integer> outputQueue;

    public DefaultOperationContext(IntCodeProgramState memory, int parameterModes, BlockingQueue<Integer> inputQueue, BlockingQueue<Integer> outputQueue) {
        this.memory = memory;
        this.parameterModes = parameterModes;
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public int nextParameter() {
        try {
            return switch (parameterModes % 10) {
                case 0 -> memory.readPositionalValue();
                case 1 -> memory.readImmediateValue();
                default -> throw new IllegalStateException(String.format("Parameter mode %d not supported.", parameterModes % 10));
            };
        } finally {
            parameterModes /= 10;
        }
    }

    @Override
    public void storeValue(int value) {
        final int position = memory.readImmediateValue();
        memory.writeValue(position, value);
    }

    @Override
    public int input() {
        log.debug("Requesting input...");
        final Integer input = Uninterruptibles.takeUninterruptibly(inputQueue);
        log.debug("Recieved input {}.", input);
        return input;
    }

    @Override
    public void output(int value) {
        Uninterruptibles.putUninterruptibly(outputQueue, value);
    }

    @Override
    public void stop() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public void jump(int position) {
        memory.jump(position);
    }
}
