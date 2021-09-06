package otus;

import config.ConfigReader;
import config.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.OtusMainPage;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FilterCoursesOnMainPageTest {

    private static WebDriver driver;
    private static final String URL = ConfigReader.readPropertyByKey("url");
    private static final String BROWSER = ConfigReader.readPropertyByKey("browser");

    @BeforeClass
    public static void setUp() {
        WebDriverFactory web_driver = new WebDriverFactory(BROWSER);
        driver = web_driver.driver;
        driver.get(URL);
        driver.manage().timeouts().pageLoadTimeout(10000,
                TimeUnit.SECONDS);
    }

    @BeforeMethod
    private void updatePage() {
        driver.get(URL);
    }

    @Test(description = "Проверка ожидания", enabled = true)
    private void waitCourses() {
        OtusMainPage otusMainPage = PageFactory.initElements(driver, OtusMainPage.class);
        otusMainPage.waitCoursesElements();
    }

    @Test(description = "Действия на вкладке курсы", enabled = true)
    public void actionsOnMainPage() {
        OtusMainPage otusMainPage = PageFactory.initElements(driver, OtusMainPage.class);
        otusMainPage.openCoursesActions();
    }

    @Test(description = "Кликаем по вкладке Курсы, проверяем подсветку", enabled = true)
    public void clickCourseMenu() {
        OtusMainPage otusMainPage = PageFactory.initElements(driver, OtusMainPage.class);
        otusMainPage.clickCourseButton();
    }

    @Test(description = "Фильтрация курсов по имени", enabled = true)
    public void getCourseName() {
        OtusMainPage otusMainPage = PageFactory.initElements(driver, OtusMainPage.class);
        String actualSpecialization = otusMainPage.getCourseNameFromMainPage("Специализация С++");
        String expectedSpecialization = "Специализация С++";
        Assert.assertEquals(actualSpecialization, expectedSpecialization);
    }

    @Test(description = "Получаем самую первую дату начала курсов", enabled = true)
    public void getFirstStartDate() throws InterruptedException {
        OtusMainPage otusMainPage = PageFactory.initElements(driver, OtusMainPage.class);
        otusMainPage.createMapCourseNameCourseDataStart();
        Date firstDate = otusMainPage.getFirstDate();
        String actualFirstDate = firstDate.toString();
        String actualCourseName = otusMainPage.findCourseNameByDate(firstDate);
        String expectedFirstDate = "Tue Aug 31 00:00:00 MSK 2021";
        String expectedCourseName = "Data Engineer";
        Assert.assertEquals(actualFirstDate, expectedFirstDate);
        Assert.assertEquals(expectedCourseName, actualCourseName);
    }

    @Test(description = "Получаем самую последнюю дату начала курсов", enabled = true)
    public void getLastStartDate() {
        OtusMainPage otusMainPage = PageFactory.initElements(driver, OtusMainPage.class);
        otusMainPage.createMapCourseNameCourseDataStart();
        Date lastDate = otusMainPage.getLastDate();
        String actualLastDate = lastDate.toString();
        String expectedLastDate = "Thu Sep 30 00:00:00 MSK 2021";
        String expectedCourseName = "Специализация Android-разработчик";
        String actualCourseName = otusMainPage.findCourseNameByDate(lastDate);
        Assert.assertEquals(actualLastDate, expectedLastDate);
        Assert.assertEquals(actualCourseName, expectedCourseName);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}