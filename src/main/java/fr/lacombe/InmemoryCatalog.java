package fr.lacombe;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class InmemoryCatalog implements PriceQuery {
    private ItemReference[] items;

    public InmemoryCatalog(ItemReference... items) {
        this.items = items;
    }

    public Result findPrice(String itemCode) {
        return Stream.of(items)
                .filter(item -> item.checkSameCode(itemCode))
                .map(ItemReference::getPrice)//classe::methode
                .map(price -> Result.found(price))//seconde maniere d'ecrire Result::found // Classe::Methode
                .findFirst()//findFirst equivalent d'un collector et renvoie le premier elt trouvé
                .orElseGet(() -> Result.notFound(itemCode));
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
