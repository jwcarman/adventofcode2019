package com.callibrity.adventofcode.geom;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;

class VectorTest {

    // Fields
    public static final double TOLERANCE = 0.00000000001;

    @Test
    void theta() {
        assertThetaOf(0, -10, 0);
        assertThetaOf(10, -10, PI / 4);
        assertThetaOf(10, 0, PI / 2);
        assertThetaOf(10, 10, 3 * PI / 4);
        assertThetaOf(0, 10, PI);
        assertThetaOf(-10, 10, 5 * PI / 4);
        assertThetaOf(-10, 0, 3 * PI / 2);
        assertThetaOf(-10, -10, 7 * PI / 4);
    }

    private void assertThetaOf(int x, int y, double expected) {
        assertThat(thetaOf(x, y)).isCloseTo(expected, Offset.offset(TOLERANCE));
    }

    private double thetaOf(int x, int y) {
        return new Vector(x, y).getTheta();
    }

    @Test
    void pointConstructor() {
        final Vector vector = new Vector(new Point(10, 10), new Point(20, 40));
        assertThat(vector.getX()).isEqualTo(10);
        assertThat(vector.getY()).isEqualTo(30);
    }

    @Test
    void reduce() {
        final Vector vector = new Vector(5, 10);
        final Vector reduced = vector.reduced();
        assertThat(reduced.getY()).isEqualTo(2);
        assertThat(reduced.getX()).isEqualTo(1);
    }

    @Test
    void reduceIrreducible() {
        final Vector vector = new Vector(3, 2);
        final Vector reduced = vector.reduced();
        assertThat(reduced).isEqualTo(vector);
        assertThat(reduced.getY()).isEqualTo(2);
        assertThat(reduced.getX()).isEqualTo(3);
    }
}