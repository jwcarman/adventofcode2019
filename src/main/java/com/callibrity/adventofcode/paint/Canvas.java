package com.callibrity.adventofcode.paint;

import com.callibrity.adventofcode.geom.Point;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class Canvas {
    private final Color defaultColor;
    private final Map<Point, Color> pixels = new HashMap<>();

    private int left = 0;
    private int right = 0;
    private int top = 0;
    private int bottom = 0;

    public Canvas() {
        this(Color.Black);
    }

    public Color paintPixel(Point location, Color color) {
        left = Math.min(left, location.getX());
        right = Math.max(right, location.getX());
        top = Math.max(top, location.getY());
        bottom = Math.min(bottom, location.getY());
        return Optional.ofNullable(pixels.put(location, color)).orElse(defaultColor);
    }

    public Color colorOfPixelAt(Point location) {
        return pixels.getOrDefault(location, defaultColor);
    }

}
