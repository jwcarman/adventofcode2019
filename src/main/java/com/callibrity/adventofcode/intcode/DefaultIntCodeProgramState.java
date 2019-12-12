package com.callibrity.adventofcode.intcode;

import java.util.List;

public class DefaultIntCodeProgramState implements IntCodeProgramState {

    private final List<Integer> memory;
    private int instructionPointer = 0;

    public DefaultIntCodeProgramState(List<Integer> memory) {
        this.memory = memory;
    }

    @Override
    public int readImmediateValue() {
        return memory.get(instructionPointer++);
    }

    @Override
    public int readPositionalValue() {
        return memory.get(memory.get(instructionPointer++));
    }

    @Override
    public void writeValue(int position, int value) {
        memory.set(position, value);
    }

    @Override
    public void jump(int position) {
        instructionPointer = position;
    }
}
