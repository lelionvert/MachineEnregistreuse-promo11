package fr.lacombe;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CashRegisterTest
{

    private Object[] parametersForCreate_cash_register() {
        return new Object[][] {
                {1.20, 0.0, 0.0},
                {1.20, 1.0, 1.20},
                {1.20, 2.0, 2.40},
                {1.20, 5.0, 6.00}
        };
    }

    @Test
    @Parameters
    public void create_cash_register(final double price, final double quantity, final double expected) {
        CashRegister cash_register = new CashRegister();

        double total = cash_register.total(price, quantity);

        Assertions.assertThat(total).isEqualTo(expected);
    }

    @Test
    public void create_cash_register_with_price_concept() {
        // Given
        CashRegister cash_register = new CashRegister();
        Price price = new Price(1.20);
        double quantity = 1;

        // When
        Price total = cash_register.total(price, quantity);

        // Then
        Price expected = price;
        Assertions.assertThat(total).isEqualTo(expected);
    }

    @Test
    public void create_cash_register_with_two_price_elements() {
        // Given
        CashRegister cash_register = new CashRegister();
        Price price = new Price(1.20);
        double quantity = 2;

        // When
        Price total = cash_register.total(price, quantity);

        // Then
        Price expected = new Price(2.40);
        Assertions.assertThat(total).isEqualTo(expected);
    }


}
