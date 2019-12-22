package com.callibrity.adventofcode.intcode;

import com.callibrity.adventofcode.intcode.ops.OperationContext;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class DefaultOperationContext implements OperationContext {

    private final IntCodeProgramState state;
    private int parameterModes;
    private boolean complete = false;
    private final Supplier<Long> inputSupplier;
    private final Consumer<Long> outputConsumer;

    public DefaultOperationContext(IntCodeProgramState state, int parameterModes, Supplier<Long> inputSupplier, Consumer<Long> outputConsumer) {
        this.state = state;
        this.parameterModes = parameterModes;
        this.inputSupplier = inputSupplier;
        this.outputConsumer = outputConsumer;
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
            case 0 -> state.writeValue(position, value);
            case 2 -> state.writeRelativeValue(position, value);
            default -> throw new IllegalArgumentException(String.format("Parameter mode %d not supported for writing.", parameterMode));
        }
    }

    @Override
    public long input() {
        log.debug("Requesting input...");
        final Long input = inputSupplier.get();
        log.debug("Recieved input {}.", input);
        return input;
    }

    @Override
    public void output(long value) {
        outputConsumer.accept(value);
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
