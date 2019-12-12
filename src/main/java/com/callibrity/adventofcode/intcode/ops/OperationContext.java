package com.callibrity.adventofcode.intcode.ops;

public interface OperationContext {
    int nextParameter();
    void storeValue(int value);
    int input();
    void output(int value);
    void stop();
    void jump(int position);

}
