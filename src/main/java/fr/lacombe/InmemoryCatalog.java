package fr.lacombe;

class InmemoryCatalog implements PriceQuery {
    InmemoryCatalog(ItemReference... itemReferences) {
    }

    @Override
    public Price findPrice(String itemCode) {
        if (itemCode.equals("BANANA")) {
            return Price.valueOf(1.9);
        }
        return Price.valueOf(1.2);
    }
}
