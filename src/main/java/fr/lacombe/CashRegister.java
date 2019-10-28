package fr.lacombe;

class CashRegister {
    Price total(Price price, double quantity) {
        return new Price(quantity * price.getValue());
    }
}
