package ru.bellintegrator.tests.selenide;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.bellintegrator.tests.selenide.helpers.Properties;
import ru.bellintegrator.tests.selenide.pages.YandexMainPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;

/**
 * @author Nikolay Bekhter.
 */
public class Tests extends BaseTests {
    /**
     * @author Nikolay Bekhter.
     * Тест выполняет поиск на сайте яндекс маркет с применением
     * фильтров по названию брендов и затем проверяет соответствие
     * результатов поиска с использованием Page Object паттерна
     * @param category название категории товара
     * @param sectionName название раздела товаров
     * @param setBrandes список брендов для фильтра
     * @param checkBrandes список брендов для проверки результатов
     */
    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @MethodSource("ru.bellintegrator.tests.selenide.helpers.DataProvider#providerCheckingMoney")
    public void checkJobYandexSearch(String category, String sectionName, List<String> setBrandes, List<String> checkBrandes) {
        open(Properties.testsProperties.yandexUrl(), YandexMainPage.class)
                .searchSection(category, sectionName)
                .checkingTitleByText(sectionName)
                .setBrands(setBrandes)
                .checkingResults(checkBrandes);
    }
}
