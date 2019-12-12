package com.callibrity.adventofcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StrictPasswordValidator {

    public boolean isValid(String password) {
        return Optional.ofNullable(password)
                .filter(p -> p.length() == 6)
                .map(p -> {
                    final StringBuilder chunk = new StringBuilder();
                    boolean doubles = false;
                    for (int i = 0; i < 6; ++i) {
                        final char c = p.charAt(i);
                        if (chunk.length() == 0 || chunk.charAt(0) == c) {
                            chunk.append(c);
                        } else if (chunk.charAt(0) > c) {
                            return false;
                        } else {
                            if(chunk.length() == 2) {
                                doubles = true;
                            }
                            chunk.setLength(0);
                            chunk.append(c);
                        }
                    }

                    return doubles || chunk.length() == 2;
                })
                .orElse(false);
    }

}
