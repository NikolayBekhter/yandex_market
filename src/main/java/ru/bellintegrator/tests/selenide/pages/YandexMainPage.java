package ru.bellintegrator.tests.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * @author Nikolay Bekhter.
 */
public class YandexMainPage extends BasePage {
    /**
     * @param categoryName название категории товаров
     * @param sectionName  название раздела товаров
     * @return возвращает страницу YandexSearchInSectionPage
     * @author Nikolay Bekhter.
     * Метод наводит курсор на categoryName и нажимает на sectionName
     */
    @Step("Наводим курсор на название категории {categoryName} и нажимаем на раздел {sectionName}")
    public YandexSearchInSectionPage searchSection(String categoryName, String sectionName) {
        $x("//div[@data-zone-name='catalog']/button").click();
        $x("//span[text()='" + categoryName + "']").hover();
        SelenideElement linkElement = $x("//a[text()='" + sectionName + "']");
        actions().keyDown(Keys.CONTROL).click(linkElement).keyUp(Keys.CONTROL).perform();
        switchTo().window(1);
        return page(YandexSearchInSectionPage.class);
    }
}
