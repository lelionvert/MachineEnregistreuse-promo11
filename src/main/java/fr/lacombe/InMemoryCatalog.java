package fr.lacombe;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

class InMemoryCatalog implements PriceQuery {
    private final List<ItemReference> itemReferences;

    InMemoryCatalog(ItemReference... itemReferences) {
        this.itemReferences = Arrays.asList(itemReferences);
    }

    @Override
    public Result findPrice(String itemCode) {
        return reduce(Result.notFound(itemCode),
                (Result result, ItemReference itemReference) ->
                        itemReference.matchSoughtItem(itemCode) ? Result.found(itemReference.getPrice()) : result,
                itemReferences);
    }

    private <T, R> T reduce(T defaultValue, BiFunction<T, R, T> reducer, Collection<R> collection) {
        T accumulator = defaultValue;
        for (R element : collection) {
            accumulator = reducer.apply(accumulator, element);
        }
        return accumulator;
    }
}
