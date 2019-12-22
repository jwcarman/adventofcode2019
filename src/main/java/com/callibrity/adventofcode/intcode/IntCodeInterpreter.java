package com.callibrity.adventofcode.intcode;

import com.callibrity.adventofcode.intcode.ops.DefaultOperationFactory;
import com.callibrity.adventofcode.intcode.ops.Operation;
import com.callibrity.adventofcode.intcode.ops.OperationFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class IntCodeInterpreter {
    private final IntCodeProgramState state;
    private final OperationFactory factory;


    public IntCodeInterpreter(long... values) {
        this(new DefaultIntCodeProgramState(values), new DefaultOperationFactory());
    }

    public IntCodeInterpreter(IntCodeProgramState state, OperationFactory factory) {
        this.state = state;
        this.factory = factory;
    }

    public void execute(Supplier<Long> inputSupplier, Consumer<Long> outputConsumer) {
        DefaultOperationContext context;
        do {
            final int opcode = Long.valueOf(state.readNextValue()).intValue();
            final int instruction = opcode % 100;
            final int parameterModes = opcode / 100;
            log.debug("Executing instruction {} with parameter modes {}.", instruction, parameterModes);
            context = new DefaultOperationContext(state, parameterModes, inputSupplier, outputConsumer);
            final Operation operation = factory.createOperation(instruction);
            operation.execute(context);
        } while (!context.isComplete());
    }

    public IntCodeProgramState getState() {
        return state;
    }
}
