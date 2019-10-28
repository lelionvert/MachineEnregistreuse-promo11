package fr.lacombe;

public class ItemReference {
    private final String name;
    private final Price price;

    public ItemReference(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
