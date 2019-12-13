package com.callibrity.adventofcode.intcode;

import com.callibrity.adventofcode.intcode.ops.OperationContext;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class DefaultOperationContext implements OperationContext {

    private final IntCodeProgramState state;
    private int parameterModes;
    private boolean complete = false;
    private final BlockingQueue<Long> inputQueue;
    private final BlockingQueue<Long> outputQueue;

    public DefaultOperationContext(IntCodeProgramState state, int parameterModes, BlockingQueue<Long> inputQueue, BlockingQueue<Long> outputQueue) {
        this.state = state;
        this.parameterModes = parameterModes;
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    private int nextParameterMode() {
        final int result = parameterModes % 10;
        parameterModes /= 10;
        return result;
    }

    @Override
    public long nextParameter() {
        return switch (nextParameterMode()) {
            case 0 -> state.readValue(state.readNextValue());
            case 1 -> state.readNextValue();
            case 2 -> state.readRelativeValue(state.readNextValue());
            default -> throw new IllegalStateException(String.format("Parameter mode %d not supported.", parameterModes % 10));
        };
    }

    @Override
    public void storeValue(long value) {
        final long position = state.readNextValue();
        final int parameterMode = nextParameterMode();
        switch (parameterMode) {
            case 0:
                state.writeValue(position, value);
                break;
            case 2:
                state.writeRelativeValue(position, value);
                break;
            default:
                throw new IllegalArgumentException(String.format("Parameter mode %d not supported for writing.", parameterMode));
        }
    }

    @Override
    public long input() {
        log.debug("Requesting input...");
        final Long input = Uninterruptibles.takeUninterruptibly(inputQueue);
        log.debug("Recieved input {}.", input);
        return input;
    }

    @Override
    public void output(long value) {
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
    public void adjustRelativeBase(long offset) {
        state.adjustRelativeBase(offset);
    }

    @Override
    public void jump(long position) {
        state.jump(position);
    }
}
