package waits;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomWaitClass {
    public static ExpectedCondition<Boolean> waitRefresh(String xpathLocator) {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver input) {
                if (input.findElements(By.xpath(xpathLocator)).size() > 0) {
                    return true;
                } else
                    input.navigate().refresh();
                return false;
            }
        };
    }
}
