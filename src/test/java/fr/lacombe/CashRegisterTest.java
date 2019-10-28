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

    @Test
    public void total_when_there_is_nothing() {
        CashRegister cash_register = new CashRegister();
        double price = 1.20;
        double quantity = 0;

        double total = cash_register.total(price, quantity);

        Assertions.assertThat(total).isEqualTo(0);
    }

    @Test
    public void total_when_there_is_two_elements() {
        CashRegister cash_register = new CashRegister();
        double price = 1.20;
        double quantity = 2;

        double total = cash_register.total(price, quantity);

        Assertions.assertThat(total).isEqualTo(2.40);
    }


}
