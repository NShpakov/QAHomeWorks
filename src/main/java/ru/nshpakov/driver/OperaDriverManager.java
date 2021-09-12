package ru.nshpakov.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import static io.github.bonigarcia.wdm.config.DriverManagerType.OPERA;
public class OperaDriverManager extends DriverManager {
    @Override
    protected WebDriver createDriver() {
        System.out.println("Иницииализация драйвера opers"); // Change to Loggers
        WebDriverManager.getInstance(OPERA).setup();

        return new OperaDriver(getOperaOptions());
    }

    private OperaOptions getOperaOptions() {
        OperaOptions options = new OperaOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-features=EnableEphemeralFlashPermission");
        options.addArguments("--disable-infobars");

        return options;
    }
}
