package com.callibrity.adventofcode.intcode;

import com.callibrity.adventofcode.input.Input;
import com.callibrity.adventofcode.input.Output;
import com.callibrity.adventofcode.intcode.ops.OperationContext;

import java.io.Reader;
import java.io.Writer;

public class DefaultOperationContext implements OperationContext {

    private final IntCodeProgramState memory;
    private int parameterModes;
    private boolean complete = false;
    private final Reader input;
    private final Writer output;

    public DefaultOperationContext(IntCodeProgramState memory, int parameterModes, Reader input, Writer output) {
        this.memory = memory;
        this.parameterModes = parameterModes;
        this.input = input;
        this.output = output;
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
        return Input.readLine(input, Integer::parseInt);
    }

    @Override
    public void output(int value) {
        Output.println(output, value);
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
