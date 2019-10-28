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
                arguments(new Price(1.2), 2.5, new Price(3)),
                arguments(new Price(1.2), 3.0, new Price(3.6))
        );
    }

    @ParameterizedTest(name = "total is {2} for price:{0} and quantity:{1}")
    @MethodSource("priceProvider")
    void cash_register_calculates_total_price(Price price, double quantity, Price result) {
        // Given
        CashRegister cashRegister = new CashRegister();

        // When
        Price total = cashRegister.total(price, quantity);

        // Then
        assertThat(total).isEqualTo(result);
    }
}
