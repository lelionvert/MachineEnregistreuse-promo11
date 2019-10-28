package fr.lacombe;

public class PriceQuery {
    private ItemReference item;
    private ItemReference[] items;

    public PriceQuery(ItemReference item) {
        this.item = item;
    }

    public PriceQuery(ItemReference... items) {
        this.items = items;
    }

    public Price findPrice(String itemCode) {
        return item.getPrice();
    }

    public Price findPriceList(String itemCode) {
        for (ItemReference item : items) {
            if(itemCode.equals(item.getName()))
                return item.getPrice();
        }
        return null;
    }
}
