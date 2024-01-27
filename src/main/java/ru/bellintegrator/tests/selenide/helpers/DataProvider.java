package ru.bellintegrator.tests.selenide.helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Nikolay Bekhter.
 */
public class DataProvider {
    /**
     * @return возвращает значения для работы теста
     * @author Nikolay Bekhter.
     */
    public static Stream<Arguments> providerCheckingMoney() {
        return Stream.of(
                Arguments.of("Электроника", "Смартфоны", List.of("Apple"), List.of("iPhone"))
        );
    }
}
