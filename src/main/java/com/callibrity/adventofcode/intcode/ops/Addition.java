package com.callibrity.adventofcode.intcode.ops;

public class Addition implements Operation {
    @Override
    public void execute(OperationContext context) {
        final int left = context.nextParameter();
        final int right = context.nextParameter();
        context.storeValue(left + right);
    }
}