package fr.lacombe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Math.round;

class Price {

    private final static Map<Double, Price> mapValuePrice = new HashMap<>();
    private final double value;

    private Price(double value) {
        this.value = value;
    }

    static Price valueOf(double value) {
        return mapValuePrice.computeIfAbsent(value, v -> new Price(roundToTwoDigits(v)));
    }

    private static double roundToTwoDigits(double value) {
        return (double) round(value * 100d) / 100d;
    }

    Price multiplyBy(double quantity) {
        return valueOf(value * quantity);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Double.compare(price1.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
