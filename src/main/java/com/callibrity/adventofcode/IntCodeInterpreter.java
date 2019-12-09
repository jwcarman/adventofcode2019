package com.callibrity.adventofcode;

import java.util.Arrays;
import java.util.List;

public class IntCodeInterpreter {

    public static void main(String[] args) {
        final List<Integer> program = Arrays.asList(
                1, 12, 2, 3,
                1, 1, 2, 3,
                1, 3, 4, 3,
                1, 5, 0, 3,
                2, 9, 1, 19,
                1, 19, 5, 23,
                1, 23, 5, 27,
                2, 27, 10, 31,
                1, 31, 9, 35,
                1, 35, 5, 39,
                1, 6, 39, 43,
                2, 9, 43, 47,
                1, 5, 47, 51,
                2, 6, 51, 55,
                1, 5, 55, 59,
                2, 10, 59, 63,
                1, 63, 6, 67,
                2, 67, 6, 71,
                2, 10, 71, 75,
                1, 6, 75, 79,
                2, 79, 9, 83,
                1, 83, 5, 87,
                1, 87, 9, 91,
                1, 91, 9, 95,
                1, 10, 95, 99,
                1, 99, 13, 103,
                2, 6, 103, 107,
                1, 107, 5, 111,
                1, 6, 111, 115,
                1, 9, 115, 119,
                1, 119, 9, 123,
                2, 123, 10, 127,
                1, 6, 127, 131,
                2, 131, 13, 135,
                1, 13, 135, 139,
                1, 9, 139, 143,
                1, 9, 143, 147,
                1, 147, 13, 151,
                1, 151, 9, 155,
                1, 155, 13, 159,
                1, 6, 159, 163,
                1, 13, 163, 167,
                1, 2, 167, 171,
                1, 171, 13, 0,
                99, 2, 0, 14, 0);
        IntCodeInterpreter interpreter = new IntCodeInterpreter();
        interpreter.execute(program);
        System.out.println(program.get(0));
    }

    public void execute(List<Integer> program) {
        for (int base = 0; !program.get(base).equals(99); base += 4) {
            final int opcode = program.get(base);
            final int inputPos1 = program.get(base + 1);
            final int inputPos2 = program.get(base + 2);
            final int input1 = program.get(inputPos1);
            final int input2 = program.get(inputPos2);
            int location = program.get(base + 3);
            final int result = switch (opcode) {
                case 1 -> input1 + input2;
                case 2 -> input1 * input2;
                default -> throw new IllegalArgumentException(String.format("Opcode %d is not supported.", opcode));
            };
            program.set(location, result);
        }
    }
}
