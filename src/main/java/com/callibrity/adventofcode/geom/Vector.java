package com.callibrity.adventofcode.geom;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.math3.util.ArithmeticUtils;

@Getter
@EqualsAndHashCode(of = {"x", "y"})
@ToString
public class Vector {
    private final int x;
    private final int y;
    private final double theta;
    private final int squaredDistance;

    public Vector(Point source, Point destination) {
        this(destination.getX() - source.getX(), destination.getY() - source.getY());
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
        this.theta = calculateTheta(x, y);
        this.squaredDistance = x * x + y * y;
    }

    private static double calculateTheta(int x, int y) {
        final double theta = Math.PI / 2 + Math.atan2(y, x);
        return theta < 0 ? theta + 2 * Math.PI : theta;
    }

    public Vector reduced() {
        final int gcd = ArithmeticUtils.gcd(x, y);
        return new Vector(x / gcd, y / gcd);
    }
}
