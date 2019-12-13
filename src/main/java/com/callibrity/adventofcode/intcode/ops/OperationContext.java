package com.callibrity.adventofcode.intcode.ops;

public interface OperationContext {
    long nextParameter();

    void storeValue(long value);

    long input();

    void output(long value);

    void stop();

    void adjustRelativeBase(long offset);

    void jump(long position);

}
