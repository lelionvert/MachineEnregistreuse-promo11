package fr.lacombe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PriceQueryTest {
    PriceQuery priceQuery = new InmemoryCatalog(
            new ItemReference("APPLE", 1.2),
            new ItemReference("BANANA", 1.9)
    );

    static Stream<Arguments> itemPriceProvider() {
        return Stream.of(
                arguments("APPLE", Price.valueOf(1.20)),
                arguments("BANANA", Price.valueOf(1.9))
        );
    }

    @ParameterizedTest
    @MethodSource("itemPriceProvider")
    void find_the_price_given_an_item_code(String itemCode, Price unitPrice) {

        // Then
        assertThat(priceQuery.findPrice(itemCode)).isEqualTo(unitPrice);
    }
}
