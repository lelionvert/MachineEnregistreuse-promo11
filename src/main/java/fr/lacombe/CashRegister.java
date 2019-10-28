package fr.lacombe;

class CashRegister {
    Price total(double price, double quantity) {
        return new Price(quantity * price);
    }
}
