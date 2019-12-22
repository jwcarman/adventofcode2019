package com.callibrity.adventofcode.geom;

import lombok.Value;

@Value
public class Point {
    private int x;
    private int y;

    public Point plus(Vector vector) {
        return new Point(x + vector.getX(), y + vector.getY());
    }
}
