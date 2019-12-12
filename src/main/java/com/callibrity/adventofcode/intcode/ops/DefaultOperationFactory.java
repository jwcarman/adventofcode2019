package com.callibrity.adventofcode.intcode.ops;

public class DefaultOperationFactory implements OperationFactory {
    public Operation createOperation(int opcode) {
        return switch (opcode) {
            case 1 -> new Addition();
            case 2 -> new Multiplication();
            case 3 -> new StoreInput();
            case 4 -> new OutputParameter();
            case 5 -> new JumpIfTrue();
            case 6 -> new JumpIfFalse();
            case 7 -> new StoreIfLessThan();
            case 8 -> new StoreIfEquals();
            case 99 -> new Stop();
            default -> throw new IllegalArgumentException(String.format("Opcode %d not supported.", opcode));
        };
    }
}
