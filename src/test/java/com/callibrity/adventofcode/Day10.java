package com.callibrity.adventofcode;

import com.callibrity.adventofcode.geom.Point;
import com.callibrity.adventofcode.geom.Vector;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SortedSetMultimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Day10 {

    // Fields
    public static final String INPUT = """
            ....#.....#.#...##..........#.......#......
            .....#...####..##...#......#.........#.....
            .#.#...#..........#.....#.##.......#...#..#
            .#..#...........#..#..#.#.......####.....#.
            ##..#.................#...#..........##.##.
            #..##.#...#.....##.#..#...#..#..#....#....#
            ##...#.............#.#..........#...#.....#
            #.#..##.#.#..#.#...#.....#.#.............#.
            ...#..##....#........#.....................
            ##....###..#.#.......#...#..........#..#..#
            ....#.#....##...###......#......#...#......
            .........#.#.....#..#........#..#..##..#...
            ....##...#..##...#.....##.#..#....#........
            ............#....######......##......#...#.
            #...........##...#.#......#....#....#......
            ......#.....#.#....#...##.###.....#...#.#..
            ..#.....##..........#..........#...........
            ..#.#..#......#......#.....#...##.......##.
            .#..#....##......#.............#...........
            ..##.#.....#.........#....###.........#..#.
            ...#....#...#.#.......#...#.#.....#........
            ...####........#...#....#....#........##..#
            .#...........#.................#...#...#..#
            #................#......#..#...........#..#
            ..#.#.......#...........#.#......#.........
            ....#............#.............#.####.#.#..
            .....##....#..#...........###........#...#.
            .#.....#...#.#...#..#..........#..#.#......
            .#.##...#........#..#...##...#...#...#.#.#.
            #.......#...#...###..#....#..#...#.........
            .....#...##...#.###.#...##..........##.###.
            ..#.....#.##..#.....#..#.....#....#....#..#
            .....#.....#..............####.#.........#.
            ..#..#.#..#.....#..........#..#....#....#..
            #.....#.#......##.....#...#...#.......#.#..
            ..##.##...........#..........#.............
            ...#..##....#...##..##......#........#....#
            .....#..........##.#.##..#....##..#........
            .#...#...#......#..#.##.....#...#.....##...
            ...##.#....#...........####.#....#.#....#..
            ...#....#.#..#.........#.......#..#...##...
            ...##..............#......#................
            ........................#....##..#........#
            """;

    @Test
    void part1() {
        log.info("Answer: {}", findMaxDetectable(scanForAsteroids(INPUT)));
    }

    @Test
    void part2() {

        SortedSetMultimap<Vector, Vector> vectorMap = Multimaps.newSortedSetMultimap(new TreeMap<>(Comparator.comparingDouble(Vector::getTheta)), () -> new TreeSet<>(Comparator.comparingInt(Vector::getSquaredDistance)));
        final Set<Point> asteroids = scanForAsteroids(INPUT);
        final Point baseLocation = findBestBaseLocation(asteroids);
        log.info("The base location is {}.", baseLocation);
        asteroids.stream()
                .filter(a -> a != baseLocation)
                .map(asteroid -> new Vector(baseLocation, asteroid))
                .forEach(v -> vectorMap.put(v.reduced(), v));
        List<Vector> zapped = new LinkedList<>();

        while (!vectorMap.isEmpty()) {
            new LinkedList<>(vectorMap.keySet()).forEach(reduced -> {
                final Vector first = vectorMap.get(reduced).first();
                vectorMap.remove(reduced, first);
                zapped.add(first);
            });
        }
        final List<Point> vaporized = zapped.stream()
                .map(baseLocation::plus)
                .collect(Collectors.toList());
        log.info("The 200th vaporized is {}.", vaporized.get(199));
    }

    private Point findBestBaseLocation(Set<Point> asteroids) {
        final Map<Point, Long> cache = new HashMap<>();
        final Function<Point, Long> maxDetectableFn = a -> uniqueVectorsFrom(asteroids, a).count();

        final ToLongFunction<Point> cachedFn = a -> cache.computeIfAbsent(a, maxDetectableFn);
        return asteroids.stream()
                .max(Comparator.comparingLong(cachedFn))
                .orElseThrow();
    }

    private long findMaxDetectable(Set<Point> asteroids) {
        return asteroids.stream()
                .mapToLong(source -> uniqueVectorsFrom(asteroids, source)
                        .count())
                .max().orElse(-1L);
    }

    private Stream<Vector> uniqueVectorsFrom(Set<Point> asteroids, Point source) {
        return asteroids.stream()
                .filter(a -> a != source)
                .map(target -> new Vector(source, target))
                .map(Vector::reduced)
                .distinct();
    }

    private Set<Point> scanForAsteroids(String input) {
        final Set<Point> asteroidLocations = new HashSet<>();

        final String[] lines = StringUtils.split(input);
        for (int y = 0; y < lines.length; y++) {
            String line = lines[y];
            for (int x = 0; x < line.length(); ++x) {
                if (line.charAt(x) == '#') {
                    asteroidLocations.add(new Point(x, y));
                }
            }
        }
        return asteroidLocations;
    }
}
