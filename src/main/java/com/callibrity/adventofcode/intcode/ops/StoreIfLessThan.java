package com.callibrity.adventofcode.intcode.ops;

public class StoreIfLessThan implements Operation {
    @Override
    public void execute(OperationContext context) {
        final long param1 = context.nextParameter();
        final long param2 = context.nextParameter();
        if (param1 < param2) {
            context.storeValue(1);
        } else {
            context.storeValue(0);
        }
    }
}
