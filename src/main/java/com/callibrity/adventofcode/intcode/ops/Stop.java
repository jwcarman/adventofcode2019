package com.callibrity.adventofcode.intcode.ops;

public class Stop implements Operation {
    @Override
    public void execute(OperationContext context) {
        context.stop();
    }
}

