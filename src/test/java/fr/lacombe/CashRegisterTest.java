package fr.lacombe;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class CashRegisterTest {
    private ItemReference apple = ItemReference.reference().withName("APPLE").withPrice(1.20).build();
    private ItemReference banana = ItemReference.reference().withName("BANANA").withPrice(1.90).build();
    private ItemReference pineapple = ItemReference.reference().withName("PINEAPPLE").withPrice(2.50).build();
    private PriceQuery priceQuery = new InmemoryCatalog(apple, banana, pineapple);

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
        assertThat(priceQuery.findPrice(item_code)).isEqualTo(Result.found(unit_price));
    }

    @Test
    public void not_found_the_price_given_an_unknown_item_code() {
        assertThat(priceQuery.findPrice("PEACH")).isEqualTo(Result.notFound("PEACH"));
    }

    private Object[] parametersForTotal_is_product_of_quantity_by_item_price_corresponding_to_existing_item_code() {
        return new Object[][]{
                {"BANANA", Quantity.valueOf(10.0), Price.valueOf(19.00)},
                {"APPLE", Quantity.valueOf(2.0), Price.valueOf(2.40)},
                {"PINEAPPLE", Quantity.valueOf(3.0), Price.valueOf(7.50)}
        };
    }

    @Test
    @Parameters
    public void total_is_product_of_quantity_by_item_price_corresponding_to_existing_item_code(final String itemCode, final Quantity quantity, final Price expected) {
        // Given
        CashRegister cash_register = new CashRegister();

        // When
        Result total = cash_register.total(priceQuery.findPrice(itemCode), quantity);

        // Then
        assertThat(total).isEqualTo(Result.found(expected));
    }

    @Test
    public void total_is_product_of_quantity_by_item_price_corresponding_to_existing_item_code_not_found() {
        // Given
        CashRegister cash_register = new CashRegister();

        // When
        Result total = cash_register.total(priceQuery.findPrice("PEACH"), Quantity.valueOf(2.0));

        // Then
        assertThat(total).isEqualTo(Result.notFound("PEACH"));
    }

    @Test
    public void test_stream_reduce_method() {
//        assertThat(factoriel(1)).isEqualTo(1);
        assertThat(factoriel(1)).isEqualTo(1);
        assertThat(factoriel(2)).isEqualTo(2);
        assertThat(factoriel(3)).isEqualTo(6);
        assertThat(factoriel(4)).isEqualTo(24);
        assertThat(factoriel(5)).isEqualTo(120);
    }

    private int factoriel(int n) {
        Stream<Integer> entiersNaturels = Stream.iterate(1, i -> i + 1);
        Stream<Integer> entiersNaturelsJusquaN = entiersNaturels
                .limit(n);
        return entiersNaturelsJusquaN
                .reduce(1, (accu, current) -> accu * current);
    }

    //Reduce ets la base de .map et .filter
    @Test
    public void infinite() {
        // values.stream().filter...

        Stream.iterate(1, i -> i + 1)
                .map(this::factoriel)
                .limit(10)
                .filter(x -> x % 4 == 0)
                .forEach(System.out::println);
    }
}
