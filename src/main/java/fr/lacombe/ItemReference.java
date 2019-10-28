package fr.lacombe;

import java.util.Optional;

class ItemReference {
    private final String itemCode;
    private final Price itemPrice;

    private ItemReference(String itemCode, Price itemPrice) {
        this.itemCode = itemCode;
        this.itemPrice = itemPrice;
    }

    static ItemReference createItemReference(String itemCode, double itemPrice) {
        return new ItemReference(itemCode, Price.valueOf(itemPrice));
    }

    Price getPriceByCode(String itemCode) {
        if (this.itemCode.equals(itemCode)) {
            return itemPrice;
        }
        return null;
    }
}
