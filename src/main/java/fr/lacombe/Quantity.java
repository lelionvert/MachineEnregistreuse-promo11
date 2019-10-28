package fr.lacombe;

import java.util.Objects;

class Quantity {
    private final double value;

    private Quantity(double value) {
        this.value = value;
    }

    static Quantity valueOf(double value) {
        return new Quantity(value);
    }

    double multiplyBy(double value) {
        return value * this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return Double.compare(quantity.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
