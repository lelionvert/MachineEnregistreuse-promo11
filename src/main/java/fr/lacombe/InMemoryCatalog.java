package fr.lacombe;

import java.util.Arrays;
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

    private Result reduce(Result notFound, BiFunction<Result, ItemReference, Result> accumulator,
                          List<ItemReference> itemReferences) {
        Result result = notFound;
        for (ItemReference itemReference : itemReferences) {
            result = accumulator.apply(result, itemReference);
        }
        return result;
    }
}
