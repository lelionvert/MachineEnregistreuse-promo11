package fr.lacombe;

import java.util.Arrays;
import java.util.List;

class InMemoryCatalog implements PriceQuery {
    private final List<ItemReference> itemReferences;

    InMemoryCatalog(ItemReference... itemReferences) {
        this.itemReferences = Arrays.asList(itemReferences);
    }

    @Override
    public Result findPrice(String itemCode) {
        for (ItemReference itemReference : itemReferences) {
            if (itemReference.matchSoughtItem(itemCode)) {
                return Result.found(itemReference.getPrice());
            }
        }
        return Result.notFound(itemCode);
    }
}
