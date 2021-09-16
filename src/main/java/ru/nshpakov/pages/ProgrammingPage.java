package ru.nshpakov.pages;

import ru.nshpakov.config.ConfigReader;
import org.openqa.selenium.WebDriver;

public class ProgrammingPage extends BasePage {
    private static String programmingUrl = ConfigReader.getStringKeyProperty("url") + "categories/programming/";

    protected ProgrammingPage(WebDriver webDriver) {
        super(webDriver);
    }
}
