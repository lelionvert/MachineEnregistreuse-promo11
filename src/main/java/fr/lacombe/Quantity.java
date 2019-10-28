package fr.lacombe;

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
}
