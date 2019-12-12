package com.callibrity.adventofcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrictPasswordValidatorTest {

    private final StrictPasswordValidator validator = new StrictPasswordValidator();

    @Test
    void onlyDoublesValid() {
        assertThat(validator.isValid("112233")).isTrue();
    }

    @Test
    void moreThanTwoInvalid() {
        assertThat(validator.isValid("123444")).isFalse();
    }

    @Test
    void doubleFollowedByMultiValid() {
        assertThat(validator.isValid("112333")).isTrue();
    }

    @Test
    void doubleAtEndOnlyValid() {
        assertThat(validator.isValid("123455")).isTrue();
    }

    @Test
    void greaterThanSixCharactersInvalid() {
        assertThat(validator.isValid("1111111")).isFalse();
    }
    @Test
    void oneDoubleWorks() {
        assertThat(validator.isValid("111122")).isTrue();
    }

}