package fr.lacombe;

public class InmemoryCatalog implements PriceQuery {
    private ItemReference[] items;

    public InmemoryCatalog(ItemReference... items) {
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
