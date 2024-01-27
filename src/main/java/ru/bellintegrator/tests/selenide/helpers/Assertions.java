package ru.bellintegrator.tests.selenide.helpers;

import io.qameta.allure.Step;

import java.util.List;

/**
 * @author Nikolay Bekhter.
 */
public class Assertions {
    /**
     * @param condition значение для валидации
     * @param messages  передаваемые сообщения
     * @author Nikolay Bekhter.
     */
    // не знаю на сколько правильно написал этот метод, проверка заканчивается
    // на первом элементе ошибок, но при этом в отчет улетает весь список ошибок
    @Step("Проверяем что нет ошибок: {messages}")
    public static void assertTrue(boolean condition, List<String> messages) {
        for (String message : messages) {
            org.junit.jupiter.api.Assertions.assertTrue(condition, message);
        }
    }

    /**
     * @param condition значение для валидации
     * @param message   передаваемое сообщение
     * @author Nikolay Bekhter.
     */
    @Step("Проверяем что есть ошибка: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(condition, message);
    }
}
