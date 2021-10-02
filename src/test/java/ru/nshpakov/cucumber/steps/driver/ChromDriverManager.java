package ru.nshpakov.cucumber.steps.driver;

import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromDriverManager extends DriverManager {

    @Override
    public WebDriver createDriver() {
        System.out.println("Иницииализация драйвера Chrome"); // Change to Loggers
        WebDriverManager.getInstance(CHROME).setup();
        return new ChromeDriver(getChromeOptions());
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-features=EnableEphemeralFlashPermission");
        options.addArguments("--disable-infobars");

        return options;
    }
}
