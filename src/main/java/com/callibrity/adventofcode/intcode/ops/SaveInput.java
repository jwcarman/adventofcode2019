package com.callibrity.adventofcode.intcode.ops;

public class SaveInput implements Operation {
    @Override
    public void execute(OperationContext context) {
        context.storeValue(context.input());
    }
}
