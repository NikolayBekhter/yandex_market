package ru.bellintegrator.tests.selenide.helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * @author Nikolay Bekhter.
 */
public class ExpectedConditions {
    /**
     * @param xpathExpression элемент для ожидания
     * @author Nikolay Bekhter.
     * Метод ждет пока появится элемент, а затем исчезнет.
     */
    @Step("Ожидание появления и исчезновения элемента {xpathExpression}.")
    public static void waitForInvisibilityOfElement(String xpathExpression) {
        SelenideElement element = $x(xpathExpression);
        element.should(Condition.appear, Duration.ofSeconds(10));
        element.should(Condition.disappear, Duration.ofSeconds(10));
    }

    /**
     * @param xpathExpression элемент для проверки
     * @return возвращает true, если элемент есть на странице
     * @author Nikolay Bekhter.
     * Метод проверяет есть ли элемент.
     */
    @Step("Проверка, что есть элемент {xpathExpression}.")
    public static boolean isElementVisibility(String xpathExpression) {
        return $$x(xpathExpression).size() > 0;
    }
}
