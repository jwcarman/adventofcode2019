package com.callibrity.adventofcode.intcode.ops;

public class OutputParameter implements Operation {

    @Override
    public void execute(OperationContext context) {
        context.output(context.nextParameter());
    }
}
