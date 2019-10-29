package fr.lacombe;

public class InmemoryCatalog implements PriceQuery {
    private ItemReference[] items;

    public InmemoryCatalog(ItemReference... items) {
        this.items = items;
    }

    public Result findPrice(String itemCode) {
        for (ItemReference item : items) {
            if (item.checkSameCode(itemCode))
                return Result.found(item.getPrice());
        }
        return Result.found(Price.valueOf(0));
    }
}
