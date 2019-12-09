package com.callibrity.adventofcode;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntCodeInterpreterTest {

    private final IntCodeInterpreter interpreter = new IntCodeInterpreter();

    @Test
    void executeWithStopCode() {
        Assertions.assertThatCode(() -> interpreter.execute(Collections.singletonList(99))).doesNotThrowAnyException();
    }

    @Test
    void executeAddition() {
        List<Integer> program = new ArrayList<>(List.of(1, 5, 6, 7, 99, 40, 50, -1));
        interpreter.execute(program);
        assertThat(program.get(7)).isEqualTo(90);
    }

    @Test
    void executeMultiplication() {
        List<Integer> program = new ArrayList<>(List.of(2, 5, 6, 7, 99, 40, 50, -1));
        interpreter.execute(program);
        assertThat(program.get(7)).isEqualTo(2000);
    }

    @Test
    void executeWithInvalidOpcode() {
        assertThatThrownBy(() -> {
            List<Integer> program = new ArrayList<>(List.of(3, 5, 6, 7, 99, 40, 50, -1));
            interpreter.execute(program);
        }).isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Opcode 3 is not supported.");


    }
}