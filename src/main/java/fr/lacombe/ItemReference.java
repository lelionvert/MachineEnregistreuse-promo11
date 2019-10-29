package fr.lacombe;

class ItemReference {
    private final String itemCode;
    private final Price itemPrice;

    private ItemReference(String itemCode, Price itemPrice) {
        this.itemCode = itemCode;
        this.itemPrice = itemPrice;
    }

    static Builder aBuilder() {
        return new Builder();
    }

    Price getPriceByCode(String itemCode) {
        if (this.itemCode.equals(itemCode)) {
            return itemPrice;
        }
        return null;
    }

    static final class Builder {
        private String itemCode;
        private Price itemPrice;

        private Builder() {
        }

        Builder withItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        Builder withItemPrice(double itemPrice) {
            this.itemPrice = Price.valueOf(itemPrice);
            return this;
        }

        ItemReference build() {
            return new ItemReference(itemCode, itemPrice);
        }
    }
}
