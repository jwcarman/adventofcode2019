package com.callibrity.adventofcode;

import java.util.List;

import static java.lang.Math.floorDiv;

public class FuelCounterUpper {

    public long countUpFuel(List<Long> masses) {
        return masses.stream()
                .mapToLong(this::calculateFuel)
                .sum();
    }

    public long calculateSimpleFuel(long mass) {
        return floorDiv(mass, 3) - 2;
    }

    public long calculateFuel(long mass) {
        if(mass < 9) {
            return 0;
        }
        final long fuel = floorDiv(mass, 3) - 2;

        return fuel + calculateFuel(fuel);
    }
}
