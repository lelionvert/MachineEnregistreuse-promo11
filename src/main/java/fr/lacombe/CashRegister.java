package fr.lacombe;

class CashRegister {
    double total(double price, double quantity) {
        return price*quantity;
    }

    Price total(Price price, double quantity) {
        return new Price(price.getPrice() * quantity);
    }
}
