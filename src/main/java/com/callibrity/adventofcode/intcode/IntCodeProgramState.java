package com.callibrity.adventofcode.intcode;

public interface IntCodeProgramState {
    int readImmediateValue();
    int readPositionalValue();
    void writeValue(int position, int value);
    void jump(int position);
}
