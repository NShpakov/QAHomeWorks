package ru.nshpakov;

import ru.nshpakov.config.ConfigReader;
import ru.nshpakov.driver.DriverFactory;
import ru.nshpakov.driver.DriverManager;
import ru.nshpakov.listeners.HighLighter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.util.Locale;

public abstract class BaseTest {
    private  DriverManager driverManager;
    private EventFiringWebDriver driver;

    @BeforeMethod
    protected void setUp() {
        initDriverManager(ConfigReader.getStringKeyProperty("browser").toUpperCase(Locale.ROOT));
        driverManager.getDriver().navigate().to(ConfigReader.getStringKeyProperty("url"));
    }

    @AfterMethod
    protected void cleanUp() {
        driverManager.quitDriver();
    }

    public WebDriver getDriver() {
        driver = new EventFiringWebDriver(driverManager.getDriver()).register(new HighLighter());
        return driver;
    }

    private synchronized void initDriverManager(String browserName) {
        if (driverManager == null) {
            try {
                driverManager = DriverFactory.valueOf(browserName).getDriverManager();
            } catch (IllegalArgumentException e) {
                new RuntimeException("Имя браузера задано некорректно!");
            }

        } else driverManager.getDriver();
    }
}
