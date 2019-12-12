package com.callibrity.adventofcode.intcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


class DefaultIntCodeProgramStateTest {

    @Test
    void readImmediateValue() {
        DefaultIntCodeProgramState state = new DefaultIntCodeProgramState(Arrays.asList(5, 20, 1, 5, 2001));
        assertThat(state.readImmediateValue()).isEqualTo(5);
        assertThat(state.readImmediateValue()).isEqualTo(20);
    }

    @Test
    void readPositionalValue() {
        DefaultIntCodeProgramState state = new DefaultIntCodeProgramState(Arrays.asList(4, 5, 1, 5, 2001, 1002));
        assertThat(state.readPositionalValue()).isEqualTo(2001);
        assertThat(state.readPositionalValue()).isEqualTo(1002);
    }

    @Test
    void writeValue() {
        DefaultIntCodeProgramState state = new DefaultIntCodeProgramState(Arrays.asList(5, 20, 1, 5, 2001));
        state.writeValue(0, 11111);
        assertThat(state.readImmediateValue()).isEqualTo(11111);
    }

    @Test
    void jump() {
        DefaultIntCodeProgramState state = new DefaultIntCodeProgramState(Arrays.asList(5, 20, 1, 5, 2001));
        state.jump(4);
        assertThat(state.readImmediateValue()).isEqualTo(2001);
    }
}