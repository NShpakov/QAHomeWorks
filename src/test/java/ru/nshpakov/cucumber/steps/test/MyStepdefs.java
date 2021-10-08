package ru.nshpakov.cucumber.steps.test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.nshpakov.cucumber.steps.config.ConfigReader;
import ru.nshpakov.cucumber.steps.driver.DriverFactory;
import ru.nshpakov.cucumber.steps.driver.DriverManager;
import ru.nshpakov.cucumber.steps.listeners.HighLighter;
import ru.nshpakov.cucumber.steps.pages.OtusMainPage;
import ru.nshpakov.cucumber.steps.pages.PreparedCoursesPage;

import java.util.Locale;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    private DriverManager driverManager;
    private EventFiringWebDriver driver;
    private OtusMainPage otusMainPage;
    private PreparedCoursesPage preparedCoursesPage;

    @Then("I find a course by name {string}")
    public String iFindACourseByName(String name) {
        return otusMainPage.filterCourseNameFromMainPage(name);
    }

    @When("I open main page")
    public void iOpenMainPage() {
        driverManager.getDriver().navigate().to(ConfigReader.getStringKeyProperty("url"));
        otusMainPage = new OtusMainPage(getDriver());
    }

    @Given("I open browser Chrome")
    public void iOpenBrowserChrome() {
        initDriverManager(ConfigReader.getStringKeyProperty("browser").toUpperCase(Locale.ROOT));
    }

    @And("I check that the expected name {string} matches the actual {string}")
    public void iCheckThatTheExpectedNameMatchesTheActual(String expectedName, String actualName) {
        assertEquals(expectedName, iFindACourseByName(actualName));
    }

    @Then("I find a course by name filter {string}")
    public void iFindACourseByNameFilter(String nameFilter) {
        otusMainPage.clickRandomCourseByNameOrPartName(nameFilter);
    }

    @Then("I close the browser and end the session")
    public void iCloseBrowser() {
        driverManager.quitDriver();
    }

    @Then("I find a course by start date {string} and check courseName {string}")
    public void iFindACourseByStartDate(String expectedStartDate, String expectedCourseName) {
        otusMainPage.getCourseByDate(expectedStartDate);
        assertEquals(expectedCourseName, otusMainPage.getCourseByDate(expectedStartDate));
        System.out.println("Имя курса : " + expectedCourseName + " . " + " Дата начала занятий : " + expectedStartDate);
    }

    @Then("I check course {int} after start date {string} then check courseName {string} and courseDate {string}")
    public void iFindCoursesAfterStartDateAndCheckCoursesNames(int assertNumber, String courseDateAfter, String expectedCourseName, String expectedDate) {
        System.out.println("Порядковый номер курса : " + assertNumber);
        otusMainPage.getAfterRecievedDate(courseDateAfter).entrySet()
                .stream()
                .forEach(System.out::println);
        assertEquals(expectedDate, otusMainPage.getAfterRecievedDate(courseDateAfter).get(expectedCourseName).toString());
    }

    @Then("I go to page prepared courses")
    public void iGoToPagePreparedCourses() {
        preparedCoursesPage = otusMainPage.openPreparedCoursesActions();
    }

    @Then("I check that page url equal to value {string}")
    public void iCheckThatPageUrlEqualToValue(String expectedUrl) {
        assertEquals(preparedCoursesPage.getCurrentUrl(), expectedUrl);
    }

    @Then("Maximum price for a course is {int} rub")
    public void iFindTheMaxPrice(int expectedMaxPrice) {
        assertEquals(expectedMaxPrice, preparedCoursesPage.getMaxPrice());
    }

    @Then("Minimum price for a course is {int} rub")
    public void iFindTheMinPrice(int expectedMaxPrice) {
        assertEquals(expectedMaxPrice, preparedCoursesPage.getMinPrice());
    }

    @Then("I display the name and price of the course in the console")
    public void iDisplayTheNameAndPriceOfTheCourseInTheConsole() {
        preparedCoursesPage.printCourseInfo();
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

    private WebDriver getDriver() {
        driver = new EventFiringWebDriver(driverManager.getDriver()).register(new HighLighter());
        return driver;
    }

}