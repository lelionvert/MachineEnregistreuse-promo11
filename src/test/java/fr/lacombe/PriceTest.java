package fr.lacombe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceTest {

    @ParameterizedTest
    @ValueSource(doubles = {2.3, 5.4, 2})
    void test_flyweight(double value) {
        Assertions.assertSame(Price.valueOf(value), Price.valueOf(value));
    }
}
