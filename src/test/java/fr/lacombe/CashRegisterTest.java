package fr.lacombe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static fr.lacombe.ItemReference.aBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class CashRegisterTest {
    private InMemoryCatalog priceQuery;
    private CashRegister cashRegister;

    static Stream<Arguments> itemReferenceProvider() {
        return Stream.of(
                arguments("APPLE", 1, 1.20),
                arguments("APPLE", 2, 1.20),
                arguments("BANANA", 10, 1.9)
        );
    }

    @BeforeEach
    void setUp() {
        priceQuery = new InMemoryCatalog(
                aBuilder().withItemCode("APPLE").withItemPrice(1.2).build(),
                aBuilder().withItemCode("BANANA").withItemPrice(1.9).build()
        );
        cashRegister = new CashRegister();
    }

    @ParameterizedTest(name = "total is {2} for price:{0} and quantity:{1}")
    @MethodSource("itemReferenceProvider")
    void cash_register_calculates_total_price(String itemCode, double quantity, double unitPrice) {

        // When
        Result total = cashRegister.total(priceQuery.findPrice(itemCode), Quantity.valueOf(quantity));

        // Then
        assertThat(total).isEqualTo(Result.found(Price.valueOf(quantity * unitPrice)));
    }

    @Test
    void total_not_found_when_item_price_not_found() {
        // When
        Result total = cashRegister.total(priceQuery.findPrice("PEACH"), Quantity.valueOf(1));

        // Then
        assertThat(total).isEqualTo(Result.notFound("PEACH"));
    }
}
