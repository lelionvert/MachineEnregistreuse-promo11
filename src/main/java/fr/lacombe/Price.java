package fr.lacombe;

import java.util.Objects;

public class Price {
    private final double price;

    private Price(double price) {

        this.price = price >= 0 ? price : Math.abs(price);
    }

    static Price valueOf(double value) {
        return new Price(value);
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
        return "Price{" +
                "price=" + price +
                '}';
    }

    Price multiplyBy(double quantity) {
        double round_price = roundTwoDecimals(price * quantity);
        return valueOf(round_price);
    }

    private double roundTwoDecimals(double value) {
        return Math.round(value * 100d) / 100d;
    }
}
