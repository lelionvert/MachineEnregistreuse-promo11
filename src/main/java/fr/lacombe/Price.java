package fr.lacombe;

import java.util.Objects;

final class Price {

    private final double value;

    Price(double value) {
        this.value = value;
    }

    double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "price=" + value;
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
