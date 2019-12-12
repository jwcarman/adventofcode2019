package com.callibrity.adventofcode.intcode.ops;

public interface OperationFactory {
    Operation createOperation(int opcode);
}
