package fr.lacombe;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InMemoryCatalog implements PriceQuery {
    private final Map<String, Price> unitPriceByItemCode;

    InMemoryCatalog(ItemReference... itemReferences) {
        this.unitPriceByItemCode = Stream.of(itemReferences)
                .collect(Collectors.toMap(
                        ItemReference::getItemCode,
                        ItemReference::getItemPrice
                ));
    }

    @Override
    public Result findPrice(String itemCode) {
        Price price = unitPriceByItemCode.get(itemCode);
        return price != null ? Result.found(price) : Result.notFound(itemCode);
    }

}
