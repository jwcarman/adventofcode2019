package com.callibrity.adventofcode.intcode.ops;

public class JumpIfTrue implements Operation {
    @Override
    public void execute(OperationContext context) {
        int param = context.nextParameter();
        int position = context.nextParameter();
        if (0 != param) {
            context.jump(position);
        }
    }
}
