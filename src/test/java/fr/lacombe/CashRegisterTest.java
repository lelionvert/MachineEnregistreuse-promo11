package fr.lacombe;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CashRegisterTest {
    private ItemReference apple = ItemReference.reference().withName("APPLE").withPrice(1.20).build();
    private ItemReference banana = ItemReference.reference().withName("BANANA").withPrice(1.90).build();
    private ItemReference pineapple = ItemReference.reference().withName("PINEAPPLE").withPrice(2.50).build();
    private PriceQuery priceQuery = new InmemoryCatalog(apple, banana, pineapple);

    private Object[] parametersForCalculate_total_amount_according_to_quantity() {
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
    public void calculate_total_amount_according_to_quantity(final Price price, final Quantity quantity, final Price expected) {
        // Given
        CashRegister cash_register = new CashRegister();

        // When
        Price total = cash_register.total(price, quantity);

        // Then
        Assertions.assertThat(total).isEqualTo(expected);
    }

    private Object[] parametersForFound_the_price_given_an_item_code() {
        return new Object[][]{
                {"APPLE", Price.valueOf(1.20)},
                {"BANANA", Price.valueOf(1.90)},
                {"PINEAPPLE", Price.valueOf(2.50)}
        };
    }
    @Test
    @Parameters
    public void found_the_price_given_an_item_code(final String item_code, final Price unit_price) {
        Assertions.assertThat(priceQuery.findPrice(item_code)).isEqualTo(Result.found(unit_price));
    }

    @Test
    public void not_found_the_price_given_an_unknown_item_code() {
        Assertions.assertThat(priceQuery.findPrice("PEACH")).isEqualTo(Result.notFound("PEACH"));
    }
}
