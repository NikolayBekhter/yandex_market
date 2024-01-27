package ru.bellintegrator.tests.selenide.helpers;

import org.aeonbits.owner.Config;

/**
 * @author Nikolay Bekhter.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:src/test/resources/tests.properties"})
public interface TestsProperties extends Config {
    /**
     * @return возвращает веб адрес яндекс маркета
     * @author Nikolay Bekhter.
     */
    @Key("yandex.url")
    String yandexUrl();
}
