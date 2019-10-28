package fr.lacombe;

public class Quantity {
    private double value;

    private Quantity(double value) {
        this.value = value;
    }

    public static Quantity valueOf(double value) {
        return new Quantity(value);
    }

    public double multiply(double price) {
        return price * this.value;
    }
}
