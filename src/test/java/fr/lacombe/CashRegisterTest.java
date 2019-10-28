package fr.lacombe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class CashRegisterTest {
    static Stream<Arguments> priceProvider() {
        return Stream.of(
                arguments(new Price(1.2), 1, new Price(1.2)),
                arguments(new Price(1.2), 2, new Price(2.4)),
                arguments(new Price(3.5), 4, new Price(14))
        );
    }

    @ParameterizedTest
    @MethodSource("priceProvider")
    void cash_register_calculates_total_price(Price price, int quantity, Price result) {
        // Given
        CashRegister cashRegister = new CashRegister();

        // When
        Price total = cashRegister.total(price, quantity);

        // Then
        assertThat(total).isEqualTo(result);
    }
}
