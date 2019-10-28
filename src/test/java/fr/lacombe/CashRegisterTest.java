package fr.lacombe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class CashRegisterTest {

    @Test
    void cash_register_returns_price_single_article() {
        // Given
        CashRegister cashRegister = new CashRegister();
        double price = 1.2, quantity = 1;

        // When
        double total = cashRegister.total(price, quantity);

        // Then
        assertThat(total).isEqualTo(1.2);
    }
}
