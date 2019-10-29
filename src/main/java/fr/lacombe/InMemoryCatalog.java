package fr.lacombe;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

class InMemoryCatalog implements PriceQuery {
    private final List<ItemReference> itemReferences;

    InMemoryCatalog(ItemReference... itemReferences) {
        this.itemReferences = Arrays.asList(itemReferences);
    }

    @Override
    public Result findPrice(String itemCode) {
        return itemReferences.stream()
                .filter(itemReference -> itemReference.matchSoughtItem(itemCode))
                .findAny()
                .map(ItemReference::getPrice)
                .map(Result::found)
                .orElse(Result.notFound(itemCode));
    }

}
