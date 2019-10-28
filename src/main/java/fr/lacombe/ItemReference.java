package fr.lacombe;

class ItemReference {
    private final String itemCode;
    private final Price itemPrice;

    ItemReference(String itemCode, double itemPrice) {
        this.itemCode = itemCode;
        this.itemPrice = Price.valueOf(itemPrice);
    }

    public String getItemCode() {
        return itemCode;
    }

    public Price getItemPrice() {
        return itemPrice;
    }
}
