package com.callibrity.adventofcode.intcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class DefaultIntCodeProgramStateTest {


    @Test
    void writeValue() {
        DefaultIntCodeProgramState state = new DefaultIntCodeProgramState(5, 20, 1, 5, 2001);
        state.writeValue(0, 11111);
        assertThat(state.readValue(0)).isEqualTo(11111);
    }

    @Test
    void jump() {
        DefaultIntCodeProgramState state = new DefaultIntCodeProgramState(5, 20, 1, 5, 2001);
        state.jump(4);
        assertThat(state.readNextValue()).isEqualTo(2001);
    }
}