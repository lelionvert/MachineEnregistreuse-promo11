package fr.lacombe;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class InmemoryCatalog implements PriceQuery {
    private ItemReference[] items;

    public InmemoryCatalog(ItemReference... items) {
        this.items = items;
    }

    public Result findPrice(String itemCode) {
        return ourReduce(
                //Initial
                Result.notFound(itemCode),
                //Function Lambda
                (result, item) ->
                {
                    if (item.checkSameCode(itemCode)) {
                        return Result.found(item.getPrice());
                    } else return result;
                },
                //Elements
                Arrays.asList(items));
    }

    private Result ourReduce(Result identity,
                             BiFunction<Result,ItemReference, Result> reducer,
                             Iterable<ItemReference> values){
            Result accumulator = identity;
            for (ItemReference value : values) {
                accumulator = reducer.apply(accumulator, value);
            }
            return accumulator;
        }
}
