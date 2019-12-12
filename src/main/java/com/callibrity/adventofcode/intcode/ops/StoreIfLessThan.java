package com.callibrity.adventofcode.intcode.ops;

public class StoreIfLessThan implements Operation {
    @Override
    public void execute(OperationContext context) {
        int param1 = context.nextParameter();
        int param2 = context.nextParameter();
        if(param1 < param2) {
            context.storeValue(1);
        } else {
            context.storeValue(0);
        }
    }
}
