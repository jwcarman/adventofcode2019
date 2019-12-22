package com.callibrity.adventofcode.paint;

import com.callibrity.adventofcode.geom.Point;
import lombok.Getter;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.callibrity.adventofcode.paint.Direction.UP;

@Getter
public class PaintingRobot implements Supplier<Long>, Consumer<Long> {

    private Direction currentDirection = UP;

    private Point location = new Point(0, 0);
    private Canvas canvas = new Canvas();

    private Consumer<Long> receiver = new Painter();

    @Override
    public void accept(Long input) {
        receiver.accept(input);
    }

    @Override
    public Long get() {
        return (long) canvas.colorOfPixelAt(location).ordinal();
    }

    private class Painter implements Consumer<Long> {
        @Override
        public void accept(Long input) {
            final Color paint = Color.values()[input.intValue()];
            canvas.paintPixel(location, paint);
            receiver = new Mover();
        }
    }

    private class Mover implements Consumer<Long> {
        @Override
        public void accept(Long input) {
            currentDirection = switch (input.intValue()) {
                case 0 -> currentDirection.rotateLeft();
                case 1 -> currentDirection.rotateRight();
                default -> throw new IllegalArgumentException("Input " + input + " not allowed by 'mover'.");
            };
            location = currentDirection.move(location);
            receiver = new Painter();
        }
    }
}
