package fr.lacombe;

import java.util.Arrays;
import java.util.List;

class InMemoryCatalog implements PriceQuery {
    private final List<ItemReference> itemReferences;

    InMemoryCatalog(ItemReference... itemReferences) {
        this.itemReferences = Arrays.asList(itemReferences);
    }

    @Override
    public Price findPrice(String itemCode) {
        for (ItemReference itemReference : itemReferences) {
            if (itemReference.getItemCode().equals(itemCode)) {
                return itemReference.getItemPrice();
            }
        }
        return null;
    }
}
