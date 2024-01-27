package ru.bellintegrator.tests.selenide.helpers;

import org.aeonbits.owner.ConfigFactory;

/**
 * @author Nikolay Bekhter.
 */
public class Properties {

    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}
