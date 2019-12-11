package com.callibrity.adventofcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void validIfAllSameDigit() {
        assertThat(validator.isValid("111111")).isTrue();
    }

    @Test
    void invalidIfDecrements() {
        assertThat(validator.isValid("223450")).isFalse();
    }

    @Test
    void invalidIfNoRepeats() {
        assertThat(validator.isValid("123789")).isFalse();
    }

    @Test
    void invalidIfLengthNotSix() {
        assertThat(validator.isValid("11111")).isFalse();
    }

    @Test
    void invalidIfNull() {
        assertThat(validator.isValid(null)).isFalse();
    }
}