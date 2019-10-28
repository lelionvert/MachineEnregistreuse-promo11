package fr.lacombe;

class ItemReference {
    private final String itemCode;
    private final Price itemPrice;

    ItemReference(String itemCode, double itemPrice) {
        this.itemCode = itemCode;
        this.itemPrice = Price.valueOf(itemPrice);
    }

    Price getPriceByCode(String itemCode) {
        if (this.itemCode.equals(itemCode)) {
            return itemPrice;
        }
        return null;
    }
}
