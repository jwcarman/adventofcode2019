package com.callibrity.adventofcode;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FuelCounterUpperTest {

    @Test
    void countUpFuel() {
        final FuelCounterUpper fuelCounterUpper = new FuelCounterUpper();
        assertThat(fuelCounterUpper.countUpFuel(List.of(12L, 14L, 1969L, 100756L))).isEqualTo(
                fuelCounterUpper.calculateFuel(12) +
                        fuelCounterUpper.calculateFuel(14) +
                        fuelCounterUpper.calculateFuel(1969) +
                        fuelCounterUpper.calculateFuel(100756)
        );
    }

    @Test
    void calculateFuel() {
        final FuelCounterUpper fuelCounterUpper = new FuelCounterUpper();
        assertThat(fuelCounterUpper.calculateFuel(14)).isEqualTo(2);
        assertThat(fuelCounterUpper.calculateFuel(1969)).isEqualTo(966);
        assertThat(fuelCounterUpper.calculateFuel(100756)).isEqualTo(50346);

    }
}