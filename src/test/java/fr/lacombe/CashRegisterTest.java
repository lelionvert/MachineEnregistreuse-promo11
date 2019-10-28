package fr.lacombe;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CashRegisterTest
{
    private Object[] parametersForCreate_cash_register_price_concept() {
        return new Object[][] {
                {Price.valueOf(-1.20), 1.0, Price.valueOf(1.20)},
                {Price.valueOf(1.20), 0.0, Price.valueOf(0.0)},
                {Price.valueOf(1.20), 1.0, Price.valueOf(1.20)},
                {Price.valueOf(1.20), 2.0, Price.valueOf(2.40)},
                {Price.valueOf(1.20), 5.0, Price.valueOf(6.00)},
                {Price.valueOf(31257), .001, Price.valueOf(31.257)}
        };
    }

    @Test
    @Parameters
    public void create_cash_register_price_concept(final Price price, final double quantity, final Price expected) {
        // Given
        CashRegister cash_register = new CashRegister();

        // When
        Price total = cash_register.total(price, quantity);

        // Then
        Assertions.assertThat(total).isEqualTo(expected);
    }

    @Test
    public void quantity_creation() {
        // Given
        double value = 1.20;
        Price price = Price.valueOf(value);
        Quantity quantity = Quantity.valueOf(2);

        // When
        Price total = new CashRegister().total(price, quantity);

        // Then
        Assertions.assertThat(total).isEqualTo(Price.valueOf(2.40));

    }
}
