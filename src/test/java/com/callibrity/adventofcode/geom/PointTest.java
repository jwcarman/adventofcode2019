package com.callibrity.adventofcode.geom;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    void plus() {
        final Point point = new Point(10, 20);
        assertThat(point.plus(new Vector(5, 7))).isEqualTo(new Point(15, 27));
    }
}