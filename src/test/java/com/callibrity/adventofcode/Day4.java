package com.callibrity.adventofcode;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class Day4 {

    @Test
    void part1() {
        final PasswordValidator validator = new PasswordValidator();
        System.out.println(IntStream.range(123257, 647016).filter(i -> validator.isValid(String.valueOf(i)))
                .count());
    }

    @Test
    void part2() {
        final StrictPasswordValidator validator = new StrictPasswordValidator();
        System.out.println(IntStream.range(123257, 647016).filter(i -> validator.isValid(String.valueOf(i)))
                .count());
    }
}
