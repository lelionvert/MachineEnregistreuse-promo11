package fr.lacombe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CashRegisterTest {
    @ParameterizedTest
    @CsvSource({"1.2, 1, 1.2", "1.2, 2, 2.4", "3.5, 4, 14"})
    void cash_register_calculates_total_price(double price, int quantity, double result) {
        // Given
        CashRegister cashRegister = new CashRegister();

        // When
        Price total = cashRegister.total(price, quantity);

        // Then
        assertThat(total).isEqualTo(new Price(result));
    }
}
