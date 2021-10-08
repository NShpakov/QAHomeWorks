package ru.nshpakov.cucumber.steps.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CustomWebDriverWait {
    private static WebDriver driver;
    private static long timeOutInSeconds;

    public CustomWebDriverWait(WebDriver driver, long timeOutInSeconds) {
        this.driver = driver;
        this.timeOutInSeconds = timeOutInSeconds;
    }

    //    public List<WebElement> waitLoadElements(List<WebElement> lstElemnts) {
//        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
//        webDriverWait.until(CustomWaitByConditions.waitLoadingElements(lstElemnts));
//        return lstElemnts ;
//    }
    public static Boolean waitLoadElements(List<WebElement> lstElemnts) {
        return new WebDriverWait(driver, timeOutInSeconds).until(CustomWaitByConditions.waitLoadingElements(lstElemnts));
    }

    public static WebElement waitLoadElement(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutInSeconds);
        webDriverWait.until(CustomWaitByConditions.waitLoadingElement(element));
        return element;
    }

}