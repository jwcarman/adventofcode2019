package com.callibrity.adventofcode.intcode;

import com.callibrity.adventofcode.intcode.ops.DefaultOperationFactory;
import com.callibrity.adventofcode.intcode.ops.Operation;
import com.callibrity.adventofcode.intcode.ops.OperationFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class IntCodeInterpreter {
    private final IntCodeProgramState state;
    private final OperationFactory factory;

    public IntCodeInterpreter(List<Integer> memory) {
        this(new DefaultIntCodeProgramState(memory), new DefaultOperationFactory());
    }

    public IntCodeInterpreter(IntCodeProgramState state, OperationFactory factory) {
        this.state = state;
        this.factory = factory;
    }

    public void execute(BlockingQueue<Integer> input, BlockingQueue<Integer> output) {
        DefaultOperationContext context;
        do {
            final int opcode = state.readImmediateValue();
            final int instruction = opcode % 100;
            final int parameterModes = opcode / 100;
            context = new DefaultOperationContext(state, parameterModes, input, output);
            final Operation operation = factory.createOperation(instruction);
            operation.execute(context);
        } while (!context.isComplete());
    }
}
