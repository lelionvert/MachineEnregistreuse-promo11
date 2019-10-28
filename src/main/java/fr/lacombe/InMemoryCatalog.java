package fr.lacombe;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class InMemoryCatalog implements PriceQuery {
    private final List<ItemReference> itemReferences;

    InMemoryCatalog(ItemReference... itemReferences) {
        this.itemReferences = Arrays.asList(itemReferences);
    }

    @Override
    public Price findPrice(String itemCode) {
        return itemReferences.stream()
                .map(itemReference -> itemReference.getPriceByCode(itemCode))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
