package com.callibrity.adventofcode.input;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Input {

    public static String readLine(Reader reader) {
        try (BufferedReader br = new BufferedReader(reader)) {
            return br.readLine();
        } catch (IOException e) {
            throw new InputException(e, "Unable to parse standard input.");
        }
    }

    public static <T> T readLine(Reader stream, Function<String, T> parser) {
        return parser.apply(readLine(stream));
    }

    public static List<String> toLines(Reader reader) {
        try (BufferedReader br = new BufferedReader(reader)) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new InputException(e, "Unable to parse standard input.");
        }
    }

    public static <T> List<T> toLines(Reader stream, Function<String, T> parser) {
        return toLines(stream).stream().map(parser).collect(Collectors.toList());
    }

    public static <T> List<T> toLines(InputStream stream, Function<String, T> parser) {
        return toLines(new InputStreamReader(stream, StandardCharsets.UTF_8), parser);
    }
}
