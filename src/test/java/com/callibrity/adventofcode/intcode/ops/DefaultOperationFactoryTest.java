package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DefaultOperationFactoryTest {

    private final DefaultOperationFactory factory = new DefaultOperationFactory();

    @Test
    void opcode1() {
        assertThat(factory.createOperation(1)).isExactlyInstanceOf(Addition.class);
    }

    @Test
    void opcode2() {
        assertThat(factory.createOperation(2)).isExactlyInstanceOf(Multiplication.class);
    }

    @Test
    void opcode3() {
        assertThat(factory.createOperation(3)).isExactlyInstanceOf(SaveInput.class);
    }

    @Test
    void opcode4() {
        assertThat(factory.createOperation(4)).isExactlyInstanceOf(OutputParameter.class);
    }

    @Test
    void opcode5() {
        assertThat(factory.createOperation(5)).isExactlyInstanceOf(JumpIfTrue.class);
    }

    @Test
    void opcode6() {
        assertThat(factory.createOperation(6)).isExactlyInstanceOf(JumpIfFalse.class);
    }

    @Test
    void opcode7() {
        assertThat(factory.createOperation(7)).isExactlyInstanceOf(StoreIfLessThan.class);
    }

    @Test
    void opcode8() {
        assertThat(factory.createOperation(8)).isExactlyInstanceOf(StoreIfEquals.class);
    }

    @Test
    void opcode99() {
        assertThat(factory.createOperation(99)).isExactlyInstanceOf(Stop.class);
    }

    @Test
    void unknownOpcode() {
        assertThatThrownBy(() -> factory.createOperation(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Opcode -1 not supported.");
    }
}