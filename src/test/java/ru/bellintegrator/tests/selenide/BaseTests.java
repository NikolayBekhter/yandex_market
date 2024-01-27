package ru.bellintegrator.tests.selenide;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import ru.bellintegrator.tests.selenide.allure.CustomAllureSelenide;

/**
 * @author Nikolay Bekhter.
 */
public class BaseTests {
    /**
     * @author Nikolay Bekhter
     * Метод выполняется перед каждым тестом. Добавляет слушателя "AllureSelenide"
     * и конфигурирует его для создания скриншотов и сохранения исходного кода страницы.
     */
    @BeforeAll
    public static void setup(){
        SelenideLogger.addListener("AllureSelenide",new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }
}
