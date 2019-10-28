package fr.lacombe;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.build.Plugin;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CashRegisterTest {
    private Object[] parametersForCreate_cash_register_price_concept() {
        return new Object[][]{
                {Price.valueOf(-1.20), Quantity.valueOf(1.0), Price.valueOf(1.20)},
                {Price.valueOf(1.20), Quantity.valueOf(0.0), Price.valueOf(0.0)},
                {Price.valueOf(1.20), Quantity.valueOf(1.0), Price.valueOf(1.20)},
                {Price.valueOf(1.20), Quantity.valueOf(2.0), Price.valueOf(2.40)},
                {Price.valueOf(1.20), Quantity.valueOf(5.0), Price.valueOf(6.00)},
                {Price.valueOf(31257), Quantity.valueOf(.001), Price.valueOf(31.257)}
        };
    }

    @Test
    @Parameters
    public void create_cash_register_price_concept(final Price price, final Quantity quantity, final Price expected) {
        // Given
        CashRegister cash_register = new CashRegister();

        // When
        Price total = cash_register.total(price, quantity);

        // Then
        Assertions.assertThat(total).isEqualTo(expected);
    }


   /* private Object[] parametersForFind_the_price_given_an_item_code() {
        return new Object[][] {
                {"APPLE",  1.20},
                {"BANANA", 1.90}
        };
    }

    @Test
    @Parameters
    public void find_the_price_given_an_item_code(String item_code, Price unit_price) {
        Plugin.Engine.Source.InMemory catalog = new Plugin.Engine.Source.InMemory(
                new Item
        )
        Assertions.assertThat(PriceQuery(item_code).)

    }*/

    @Test
    public void find_the_price_given_one_item_code() {
        ItemReference apple = new ItemReference("APPLE", Price.valueOf(1.20));
        String itemCode = "APPLE";
        PriceQuery priceQuery = new PriceQuery(apple);

        Assertions.assertThat(priceQuery.findPrice(itemCode)).isEqualTo(Price.valueOf(1.20));
    }
}
