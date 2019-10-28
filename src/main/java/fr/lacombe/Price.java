package fr.lacombe;

import java.util.Objects;

public class Price {
    private final double price;

    private Price(double price) {
        this.price = price >= 0 ? price : Math.abs(price);
    }

    static Price valueOf(double value) {
        return new Price(roundTwoDecimals(value));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price1 = (Price) o;
        return Double.compare(price1.price, this.price) == 0;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public String toString() {
        return "Price { price=" + price + '}';
    }

    Price multiplyBy(double quantity) {
        return valueOf(price * quantity);
    }

    private static double roundTwoDecimals(double value) {
        return Math.round(value * 100d) / 100d;
    }
}
