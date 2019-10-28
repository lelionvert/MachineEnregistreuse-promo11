package fr.lacombe;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Unit test for simple CashRegister.
 */
public class CashRegisterTest
{
    @Test
    public void create_cash_register() {
        CashRegister cash_register = new CashRegister();
        double price = 1.20;
        double quantity = 1;

        double total = cash_register.total(price, quantity);

        Assertions.assertThat(total).isEqualTo(1.20);
    }


}
