package fr.lacombe;

import java.util.Objects;
import java.util.function.UnaryOperator;

public abstract class Result {
    public static Found found(Price price) {
        return new Found(price);
    }

    public static NotFound notFound(String itemCode) {
        return new NotFound(itemCode);
    }

    public abstract Result map(UnaryOperator<Price> f);

    private static class Found extends Result {
        private final Price price;

        private Found(Price price) {
            this.price = price;
        }

        public Result map(UnaryOperator<Price> f) {
            return found(f.apply(this.price));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Found found = (Found) o;
            return Objects.equals(price, found.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(price);
        }

        @Override
        public String toString() {
            return "Found {" +
                    price +
                    '}';
        }
    }

    private static class NotFound extends Result {
        private final String itemCode;

        private NotFound(String itemCode) {
            this.itemCode = itemCode;
        }

        public Result map(UnaryOperator<Price> f) {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NotFound notFound = (NotFound) o;
            return Objects.equals(itemCode, notFound.itemCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemCode);
        }

        @Override
        public String toString() {
            return "NotFound{" +
                    itemCode + '\'' +
                    '}';
        }
    }
}
