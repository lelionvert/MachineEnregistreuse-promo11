package fr.lacombe;

import java.util.Objects;

public final class Price {
    private final double price;

    Price(double price) {
        this.price = price >= 0 ? price : Math.abs(price);
    }

    final double getPrice() {
        return price;
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
}
