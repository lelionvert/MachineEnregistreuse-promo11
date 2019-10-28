package fr.lacombe;

class ItemReference {
    private final String apple;
    private final Price itemPrice;

    ItemReference(String apple, double v) {
        this.apple = apple;
        this.itemPrice = Price.valueOf(v);
    }
}
