package fr.lacombe;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

abstract class Result {

    static Result found(Price unitPrice) {
        return new Found(unitPrice);
    }

    static Result notFound(String itemCode) {
        return new NotFound(itemCode);
    }

    abstract Result map(UnaryOperator<Price> function);

    void ifFound(Consumer<Price> consumer) {
    }

    void ifNotFound(Consumer<String> consumer) {
    }

    private static class Found extends Result {
        private final Price unitPrice;

        private Found(Price unitPrice) {
            this.unitPrice = unitPrice;
        }

        @Override
        Result map(UnaryOperator<Price> function) {
            return Result.found(function.apply(unitPrice));
        }

        @Override
        void ifFound(Consumer<Price> consumer) {
            consumer.accept(unitPrice);
        }

        @Override
        public String toString() {
            return "Found=" + unitPrice;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Found found = (Found) o;
            return unitPrice.equals(found.unitPrice);
        }

        @Override
        public int hashCode() {
            return Objects.hash(unitPrice);
        }
    }

    private static class NotFound extends Result {
        private final String itemCode;

        private NotFound(String itemCode) {
            this.itemCode = itemCode;
        }

        @Override
        Result map(UnaryOperator<Price> function) {
            return this;
        }

        @Override
        void ifNotFound(Consumer<String> consumer) {
            consumer.accept(itemCode);
        }

        @Override
        public String toString() {
            return "NotFound=" + itemCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NotFound notFound = (NotFound) o;
            return itemCode.equals(notFound.itemCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemCode);
        }
    }
}
