package com.callibrity.adventofcode;

import com.callibrity.adventofcode.input.Input;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.floorDiv;

public class FuelCounterUpper {

    public long countUpFuel(List<Long> masses) {
        return masses.stream()
                .mapToLong(this::calculateFuel)
                .sum();
    }

    public long calculateFuel(long mass) {
        return floorDiv(mass, 3) - 2;
    }

    public static void main(String[] args) {
        FuelCounterUpper fuelCounterUpper = new FuelCounterUpper();
        System.out.println(fuelCounterUpper.countUpFuel(Input.toLines(System.in, Long::parseLong)));
    }
}
