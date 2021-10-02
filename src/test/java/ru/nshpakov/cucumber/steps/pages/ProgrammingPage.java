package ru.nshpakov.cucumber.steps.pages;

import org.openqa.selenium.WebDriver;
import ru.nshpakov.cucumber.steps.config.ConfigReader;

public class ProgrammingPage extends BasePage {
    private static String programmingUrl = ConfigReader.getStringKeyProperty("url") + "categories/programming/";

    protected ProgrammingPage(WebDriver webDriver) {
        super(webDriver);
    }
}
