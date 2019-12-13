package com.callibrity.adventofcode.intcode.ops;

public class Addition implements Operation {
    @Override
    public void execute(OperationContext context) {
        final long left = context.nextParameter();
        final long right = context.nextParameter();
        context.storeValue(left + right);
    }
}
