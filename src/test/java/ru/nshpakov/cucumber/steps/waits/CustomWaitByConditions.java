package ru.nshpakov.cucumber.steps.waits;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

class CustomWaitByConditions {

    public static ExpectedCondition<Boolean> waitLoadingElements(List<WebElement> lstElemnts) {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                if (!lstElemnts.isEmpty()) {
                    return true;
                } else
                    input.navigate().refresh();
                return false;
            }
        };
    }

    public static ExpectedCondition<Boolean> waitLoadingElement(WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                if (element.isDisplayed()) {
                    return true;
                } else
                    input.navigate().refresh();
                return false;
            }
        };
    }

}
