package ru.nshpakov.pages;

import ru.nshpakov.config.Constants;
import org.openqa.selenium.WebDriver;

public class ProgrammingPage extends BasePage {
    private static String programmingUrl = Constants.getBaseUrl() + "categories/programming/";

    protected ProgrammingPage(WebDriver webDriver) {
        super(webDriver);
    }
}
