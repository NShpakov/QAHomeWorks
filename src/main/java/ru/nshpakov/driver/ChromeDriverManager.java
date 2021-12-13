package ru.nshpakov.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.nshpakov.config.ConfigReader;

import java.net.MalformedURLException;
import java.net.URI;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected WebDriver createDriver() {
        System.out.println("Иницииализация драйвера Chrome"); // Change to Loggers
        WebDriverManager.getInstance(CHROME).setup();
        if (ConfigReader.getStringKeyProperty("useSelenoidServerFlag").equals("Y")) {
            try {
                return new RemoteWebDriver(URI.create("http://selenoid:4444/wd/hub").toURL(), setChromeCapabilities());
            } catch (MalformedURLException e) {
                new RuntimeException(e);
            }
        } else {

            return new ChromeDriver(getChromeOptions());
        }
        return null;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return options;
    }

    private DesiredCapabilities setChromeCapabilities() {
        DesiredCapabilities chromeCapabilities = new DesiredCapabilities();
        chromeCapabilities.setBrowserName("chrome");
        chromeCapabilities.setVersion("95.0");
        chromeCapabilities.setCapability("enableVNC", true);
        chromeCapabilities.setCapability("enableVideo", false);
        return chromeCapabilities;

    }
}