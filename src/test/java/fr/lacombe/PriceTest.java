package fr.lacombe;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PriceTest {
    @Test
    public void prix_methode_de_fabrique_statique() {
        // Given
        CashRegister cash_register = new CashRegister();
        double value = 1.20;
        double quantity = 2;
        Price price = Price.valueOf(value);

        // When
        Price total = cash_register.total(price, quantity);

        // Then
        Assertions.assertThat(total).isEqualTo(Price.valueOf(2.40));
    }
}
