package fr.lacombe;

public class PriceQuery {
    private final ItemReference item;

    public PriceQuery(ItemReference item) {
        this.item = item;
    }

    public Price findPrice(String itemCode) {
        return item.getPrice();
    }
}
