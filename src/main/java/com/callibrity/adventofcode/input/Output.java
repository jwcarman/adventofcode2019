package com.callibrity.adventofcode.input;

import java.io.PrintWriter;
import java.io.Writer;

public class Output {
    public static void println(Writer writer, Object value) {
        try (PrintWriter pr = new PrintWriter(writer)) {
            pr.println(value);
        }
    }
}
