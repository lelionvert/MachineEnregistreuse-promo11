package fr.lacombe;

public class ItemReference {
    private final String name;
    private final Price price;

    static Builder reference() {
        return new Builder();
    }

    private ItemReference(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public boolean checkSameCode(String itemCode) {
        return itemCode.equals(this.name);
    }


    public static class Builder {
        private String name;
        private Price price;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPrice(double price) {
            this.price = Price.valueOf(price);
            return this;
        }

        public ItemReference build() {
            return new ItemReference(name, price);
        }
    }
}
