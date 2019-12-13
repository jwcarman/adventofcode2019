package com.callibrity.adventofcode.intcode;

public interface IntCodeProgramState {
    long readRelativeValue(long position);

    //    long readImmediateValue();
////    long readPositionalValue();
////    long readRelativeValue();
    long readNextValue();

    long readValue(long position);

    void writeValue(long position, long value);

    void writeRelativeValue(long position, long value);

    void jump(long position);

    void adjustRelativeBase(long offset);
}
