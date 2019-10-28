package fr.lacombe;

class CashRegister {
    Price total(Price price, Quantity quantity) {
        return price.multiplyBy(quantity);
    }
}
