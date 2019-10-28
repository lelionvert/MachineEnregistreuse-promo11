package fr.lacombe;

import static java.lang.Math.round;

class CashRegister {
    Price total(Price price, double quantity) {
        double value = price.getValue() * quantity;

        return new Price((double) round(value * 100d) / 100d);
    }
}
