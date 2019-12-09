package com.callibrity.adventofcode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WirePathsTest {

    @Test
    void invalidDirection() {
        assertThatThrownBy(() -> new WirePaths.Path("Q123"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void up() {
        WirePaths.Path path = new WirePaths.Path("U2");
        assertThat(path.hasVisited(new WirePaths.Point(0, 1))).isTrue();
        assertThat(path.hasVisited(new WirePaths.Point(0, 2))).isTrue();
    }

    @Test
    void down() {
        WirePaths.Path path = new WirePaths.Path("D2");
        assertThat(path.hasVisited(new WirePaths.Point(0, -1))).isTrue();
        assertThat(path.hasVisited(new WirePaths.Point(0, -2))).isTrue();
    }


    @Test
    void left() {
        WirePaths.Path path = new WirePaths.Path("L2");
        assertThat(path.hasVisited(new WirePaths.Point(-1, 0))).isTrue();
        assertThat(path.hasVisited(new WirePaths.Point(-2, 0))).isTrue();
    }

    @Test
    void right() {
        WirePaths.Path path = new WirePaths.Path("R2");
        assertThat(path.hasVisited(new WirePaths.Point(1, 0))).isTrue();
        assertThat(path.hasVisited(new WirePaths.Point(2, 0))).isTrue();
    }

    @Test
    void intersectionEmpty() {
        WirePaths.Path path1 = new WirePaths.Path("R2");
        WirePaths.Path path2 = new WirePaths.Path("L2");
        assertThat(path1.intersection(path2)).isEmpty();
    }

    @Test
    void intersection() {
        WirePaths.Path path1 = new WirePaths.Path("R2,U2");
        WirePaths.Path path2 = new WirePaths.Path("U2,R2");
        assertThat(path1.intersection(path2))
                .hasSize(1)
                .contains(new WirePaths.Point(2, 2));
    }

}