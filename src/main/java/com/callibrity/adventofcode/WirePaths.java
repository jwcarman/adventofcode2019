package com.callibrity.adventofcode;

import com.google.common.collect.Sets;
import lombok.Value;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class WirePaths {

    private static final Point ORIGIN = new Point(0, 0);

    @Value
    public static class Point {
        private int x;
        private int y;
    }

    public static class Path {
        private final List<Point> visited = new LinkedList<>();

        private Point currentLocation = ORIGIN;

        public Path(String descriptor) {
            Stream.of(descriptor.split(","))
                    .map(String::trim)
                    .forEach(this::execute);
        }

        public boolean hasVisited(Point point) {
            return visited.contains(point);
        }

        public int stepsTo(Point point) {
            return visited.indexOf(point) + 1;
        }

        private void execute(String command) {
            final Function<Point, Point> xform = switch (command.charAt(0)) {
                case 'U' -> p -> new Point(p.x, p.y + 1);
                case 'D' -> p -> new Point(p.x, p.y - 1);
                case 'L' -> p -> new Point(p.x - 1, p.y);
                case 'R' -> p -> new Point(p.x + 1, p.y);
                default -> throw new IllegalArgumentException(String.format("Command %s not supported", command));
            };
            final int count = Integer.parseInt(command.substring(1));
            for (int i = 0; i < count; ++i) {
                currentLocation = xform.apply(currentLocation);
                visited.add(currentLocation);
            }
        }

        public Set<Point> intersection(Path other) {
            return Sets.intersection(new HashSet<>(visited), new HashSet<>(other.visited));
        }
    }
}
