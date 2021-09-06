package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import listeners.HighLighter;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory {
    public static EventFiringWebDriver driver;
    private static String Browser_name;
    public static EventFiringWebDriver eventDriver;

    public WebDriverFactory(String browser) {
        Browser_name = browser;
        initBrowser();
        driver.register(new HighLighter());
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }


    public static void initBrowser() {
        {
            switch (Browser_name) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new EventFiringWebDriver(new ChromeDriver());
                    break;
                case "firefox":
                    driver = new EventFiringWebDriver(new FirefoxDriver());
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new EventFiringWebDriver(new OperaDriver());
                    break;
                default:
                    throw new IllegalArgumentException("In the 'configuration.properties' file, the value of the 'browser' property should be: 'chrome','firefox' or 'opera'");

            }

        }
    }
}