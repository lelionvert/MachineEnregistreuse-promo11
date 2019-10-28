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
        return itemReferences.stream()
                .filter(itemReference -> itemReference.getPriceByCode(itemCode) != null)
                .findFirst()
                .map(itemReference -> itemReference.getPriceByCode(itemCode))
                .orElse(null);
    }
}
