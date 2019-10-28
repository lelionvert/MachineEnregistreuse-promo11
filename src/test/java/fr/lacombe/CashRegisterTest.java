package fr.lacombe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class CashRegisterTest {
    static Stream<Arguments> priceProvider() {
        return Stream.of(
                arguments(Price.valueOf(1.2), Quantity.of(1), Price.valueOf(1.2)),
                arguments(Price.valueOf(1.2), Quantity.of(2.5), Price.valueOf(3)),
                arguments(Price.valueOf(1.2), Quantity.of(3.0), Price.valueOf(3.6))
        );
    }

    @ParameterizedTest(name = "total is {2} for price:{0} and quantity:{1}")
    @MethodSource("priceProvider")
    void cash_register_calculates_total_price(Price price, Quantity quantity, Price result) {
        // Given
        CashRegister cashRegister = new CashRegister();

        // When
        Price total = cashRegister.total(price, quantity);

        // Then
        assertThat(total).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2.3, 5.4, 2, 0, 7.9})
    void test_flyweight(double value) {
        Assertions.assertSame(Price.valueOf(value), Price.valueOf(value));
    }
}
