package ru.nshpakov.cucumber.steps.utils;
import org.openqa.selenium.WebDriver;

public class PageHelper {
    public static String getPageUrl(WebDriver webDriver) {
        return webDriver.getCurrentUrl();
    }
}
