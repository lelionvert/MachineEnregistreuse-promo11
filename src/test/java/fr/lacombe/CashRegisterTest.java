package fr.lacombe;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
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

    private ItemReference apple = ItemReference.reference().withName("APPLE").withPrice(1.20).build();
    private ItemReference banana = ItemReference.reference().withName("BANANA").withPrice(1.90).build();
    private PriceQuery priceQuery = new InmemoryCatalog(apple, banana);

    private Object[] parametersForFind_the_price_given_one_item_code_with_Result_concept() {
        return new Object[][]{
                {"APPLE", Price.valueOf(1.20)},
                {"BANANA", Price.valueOf(1.90)}
        };
    }
    @Test
    @Parameters
    public void find_the_price_given_one_item_code_with_Result_concept(String item_code, Price unit_price) {
        Assertions.assertThat(priceQuery.findPrice(item_code)).isEqualTo(Result.found(unit_price));
    }

    @Test
    public void search_an_unknow_item() {
        Assertions.assertThat(priceQuery.findPrice("PEACH")).isEqualTo(Result.notFound("PEACH"));
    }

}
