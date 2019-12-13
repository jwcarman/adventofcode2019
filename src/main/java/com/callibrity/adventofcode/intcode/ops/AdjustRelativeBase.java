package com.callibrity.adventofcode.intcode.ops;

public class AdjustRelativeBase implements Operation {
    @Override
    public void execute(OperationContext context) {
        context.adjustRelativeBase(context.nextParameter());
    }
}
