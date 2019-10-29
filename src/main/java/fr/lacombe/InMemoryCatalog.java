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
                (Result result, ItemReference itemReference) -> {
                    if (itemReference.matchSoughtItem(itemCode)) {
                        return Result.found(itemReference.getPrice());
                    } else {
                        return result;
                    }
                }, itemReferences);
    }

    private <T, R> T reduce(T defaultValue, BiFunction<T, R, T> accumulator, Collection<R> collection) {
        T result = defaultValue;
        for (R element : collection) {
            result = accumulator.apply(result, element);
        }
        return result;
    }
}
