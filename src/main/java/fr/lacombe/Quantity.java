package fr.lacombe;

class Quantity {
    private final double value;

    private Quantity(double value) {
        this.value = value;
    }

    static Quantity of(double value) {
        return new Quantity(value);
    }

    double getValue() {
        return value;
    }
}
