package com.callibrity.adventofcode;

import java.util.Optional;

public class PasswordValidator {

    public boolean isValid(String password) {
        return Optional.ofNullable(password)
                .filter(p -> p.length() == 6)
                .map(p -> {
                    final StringBuilder chunk = new StringBuilder();
                    boolean multi = false;
                    for (int i = 0; i < 6; ++i) {
                        final char c = p.charAt(i);
                        if (chunk.length() == 0 || chunk.charAt(0) == c) {
                            chunk.append(c);
                        } else if (chunk.charAt(0) > c) {
                            return false;
                        } else {
                            if(chunk.length() > 1) {
                                multi = true;
                            }
                            chunk.setLength(0);
                            chunk.append(c);
                        }
                    }

                    return multi || chunk.length() > 1;
                })
                .orElse(false);
    }


}
