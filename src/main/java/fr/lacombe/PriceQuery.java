package fr.lacombe;

public class PriceQuery {
    private ItemReference[] items;

    public PriceQuery(ItemReference... items) {
        this.items = items;
    }

    public Price findPrice(String itemCode) {
        for (ItemReference item : items) {
            if(itemCode.equals(item.getName()))
                return item.getPrice();
        }
        return null;
    }
}
