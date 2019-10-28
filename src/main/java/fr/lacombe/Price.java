package fr.lacombe;

import java.util.Objects;

final class Price {

    private final double amount;

    Price(double amount) {
        this.amount = amount;
    }

    double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "price=" + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return Double.compare(price1.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
