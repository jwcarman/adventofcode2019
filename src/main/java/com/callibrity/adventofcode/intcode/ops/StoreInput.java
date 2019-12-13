package com.callibrity.adventofcode.intcode.ops;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoreInput implements Operation {
    @Override
    public void execute(OperationContext context) {
        final long input = context.input();
        context.storeValue(input);
    }
}
