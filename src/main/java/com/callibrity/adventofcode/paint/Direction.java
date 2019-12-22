package com.callibrity.adventofcode.paint;

import com.callibrity.adventofcode.geom.Point;
import com.callibrity.adventofcode.geom.Vector;

public enum Direction {
    UP {
        @Override
        public Direction rotateLeft() {
            return LEFT;
        }

        @Override
        public Direction rotateRight() {
            return RIGHT;
        }

        @Override
        public Point move(Point from) {
            return from.plus(new Vector(0, 1));
        }
    },

    LEFT {
        @Override
        public Direction rotateLeft() {
            return DOWN;
        }

        @Override
        public Direction rotateRight() {
            return UP;
        }

        @Override
        public Point move(Point from) {
            return from.plus(new Vector(-1, 0));
        }
    },

    RIGHT {
        @Override
        public Direction rotateLeft() {
            return UP;
        }

        @Override
        public Direction rotateRight() {
            return DOWN;
        }

        @Override
        public Point move(Point from) {
            return from.plus(new Vector(1, 0));
        }
    },

    DOWN {
        @Override
        public Direction rotateLeft() {
            return RIGHT;
        }

        @Override
        public Direction rotateRight() {
            return LEFT;
        }

        @Override
        public Point move(Point from) {
            return from.plus(new Vector(0, -1));
        }
    };

    public abstract Direction rotateLeft();

    public abstract Direction rotateRight();

    public abstract Point move(Point from);
}
