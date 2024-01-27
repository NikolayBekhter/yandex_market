package ru.bellintegrator.tests.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.bellintegrator.tests.selenide.helpers.Assertions;
import ru.bellintegrator.tests.selenide.helpers.ExpectedConditions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * @author Nikolay Bekhter.
 */
public class YandexSearchInSectionPage extends YandexMainPage {
    /**
     * @param sectionName название раздела для проверки
     * @return возвращает страницу YandexSearchInSectionPage
     * @author Nikolay Bekhter.
     * Метод проверяет открылся ли раздел sectionName.
     */
    @Step("Проверка, что открылся раздел {sectionName}")
    public YandexSearchInSectionPage checkingTitleByText(String sectionName) {
        ElementsCollection titles = $$x("//div[@data-zone-name='searchTitle']//h1[text()='" + sectionName + "']");
        Assertions.assertFalse(titles.size() == 0,
                "Раздел " + sectionName + " не открылся.");
        return this;
    }

    /**
     * @param brands список возможных брендов для фильтра
     * @return возвращает страницу YandexSearchInSectionPage
     * @author Nikolay Bekhter.
     * Метод устанавливает название брендов для фильтра.
     */
    @Step("Установка брендов {brands}")
    public YandexSearchInSectionPage setBrands(List<String> brands) {
        for (String brand : brands) {
            $x("//div[@data-filter-type='enum']//span[text()= '" + brand + "']").hover();
            $x("//div[@data-filter-type='enum']//span[text()= '" + brand + "']").click();
            ExpectedConditions.waitForInvisibilityOfElement("//div[@data-grabber='SearchSerp']//div[@data-auto='preloader']");
        }
        return this;
    }

    /**
     * @param brands список брендов для проверки
     * @return возвращает страницу YandexSearchInSectionPage
     * @author Nikolay Bekhter.
     * Проверяет результаты поиска на наличие брендов, не соответствующих фильтру
     */
    @Step("Проверка, что после поиска есть только бренды {brands}")
    public YandexSearchInSectionPage checkingResults(List<String> brands) {
        LocalDateTime currentTime = LocalDateTime.now();
        List<String> listErrors = new ArrayList<>();
        int page = 1;

        while (true) {
            $x("//div[@data-baobab-name='pager']").hover();

            ElementsCollection results = $$x("//article[@data-autotest-id='product-snippet']//h3[@data-baobab-name='title']//span");
            List<String> otherBrands = otherBrands(results, brands);

            if (otherBrands.size() != 0) {
                for (String brand : otherBrands) {
                    listErrors.add("На странице " + page + " есть " + brand + ", что не соответствует фильтру.");
                }
            }

            if (Duration.between(currentTime, LocalDateTime.now()).toMinutes() > 10) {
                break;
            }

            if (!ExpectedConditions.isElementVisibility("//div[@data-auto='pagination-next']")) {
                break;
            }
            $x("//div[@data-auto='pagination-next']//span[text()='Вперёд']").click();
            ExpectedConditions.waitForInvisibilityOfElement("//div[@data-grabber='SearchSerp']//div[@data-auto='preloader']");
            page++;
        }
        Assertions.assertTrue(listErrors.isEmpty(), listErrors);
        return this;
    }

    /**
     * @param elements результаты поиска для проверки
     * @param brands   бренды для проверки
     * @return возвращает список брендов, не соответствующих фильтру
     * @author Nikolay Bekhter.
     * Метод собирает в список результаты поиска, в которых нет переданных брендов.
     */
    @Step("Проверка, содержатся ли в результатах поиска бренды, помимо {brands}")
    public List<String> otherBrands(ElementsCollection elements, List<String> brands) {
        return elements.stream()
                .map(SelenideElement::getText)
                .filter(text -> brands.stream()
                        .noneMatch(brand -> text.toUpperCase().contains(brand.toUpperCase())))
                .collect(Collectors.toList());
    }
}
