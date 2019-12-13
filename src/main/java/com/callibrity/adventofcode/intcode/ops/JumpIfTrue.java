package com.callibrity.adventofcode.intcode.ops;

public class JumpIfTrue implements Operation {
    @Override
    public void execute(OperationContext context) {
        final long param = context.nextParameter();
        final long position = context.nextParameter();
        if (0 != param) {
            context.jump(position);
        }
    }
}
