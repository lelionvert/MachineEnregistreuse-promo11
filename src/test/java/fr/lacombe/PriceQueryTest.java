package fr.lacombe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PriceQueryTest {
    private InMemoryCatalog priceQuery;

    @BeforeEach
    void setUp() {
        priceQuery = new InMemoryCatalog(
                ItemReference.createItemReference("APPLE", 1.2),
                ItemReference.createItemReference("BANANA", 1.9)
        );
    }

    static Stream<Arguments> itemPriceProvider() {
        return Stream.of(
                arguments("APPLE", Price.valueOf(1.20)),
                arguments("BANANA", Price.valueOf(1.9))
        );
    }

    @ParameterizedTest
    @MethodSource("itemPriceProvider")
    void find_the_price_given_an_item_code(String itemCode, Price unitPrice) {
        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(unitPrice);
    }

    @Test
    void search_an_unknown_item() {
        assertThat(priceQuery.findPrice("PEACH")).isNull();
    }
}
