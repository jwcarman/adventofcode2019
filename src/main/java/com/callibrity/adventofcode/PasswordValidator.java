package com.callibrity.adventofcode;

import java.util.Optional;
import java.util.stream.IntStream;

public class PasswordValidator {

    public boolean isValid(String password) {
        return Optional.ofNullable(password)
                .filter(p -> p.length() == 6)
                .map(p -> {
                    boolean repeat = false;
                    for (int i = 1; i < 6; ++i) {
                        final int comparison = p.charAt(i) - p.charAt(i - 1);
                        if (comparison < 0) {
                            return false;
                        } else if (comparison == 0) {
                            repeat = true;
                        }
                    }
                    return repeat;
                })
                .orElse(false);
    }

    public static void main(String[] args) {
        final PasswordValidator validator = new PasswordValidator();
        System.out.println(IntStream.range(123257, 647016).filter(i -> validator.isValid(String.valueOf(i)))
                .count());
    }
}
