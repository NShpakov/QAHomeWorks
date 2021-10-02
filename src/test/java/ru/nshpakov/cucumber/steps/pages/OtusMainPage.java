package ru.nshpakov.cucumber.steps.pages;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.nshpakov.cucumber.steps.utils.DataParser;
import ru.nshpakov.cucumber.steps.waits.CustomWebDriverWait;

import java.util.*;
import java.util.stream.Collectors;

public class OtusMainPage extends BasePage {
    private final static String POPULAR_COURSES_DATES_LOCATOR = ".//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following::div[@class='lessons'][1]/a/div/div[@class='lessons__new-item-bottom']/div[@class='lessons__new-item-start']";
    private final static String POPULAR_COURSES_NAMES_LOCATOR = "./ancestor::div[@class='lessons__new-item-container']/div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse']";
    private static final String COURSE_BUTTON = "/html/body/div[1]/div/header[2]/div/div[2]/div[1]";
    private static final String COOKIE_BUTTON = ".//button[@class='js-cookie-accept cookies__button']";
    private static final String PREPARED_COURSES_BUTTON = "(.//a[@title='Подготовительные курсы'])[1]";
    private static final String SEARCH_COURSE_BY_NAME_LOCATOR = ".//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following::div[@class='lessons'][1]/a/div/div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse'][contains(text(), '')]";
    private static final String POPULAR_COURSES_URL_LOCATOR = ".//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following-sibling::div[@class='lessons'][1]/a[@href]";
    private Actions actions = new Actions(webDriver);
    @FindBy(xpath = COURSE_BUTTON)
    WebElement coursesButton;
    @FindBy(xpath = COOKIE_BUTTON)
    WebElement cookieButton;
    @FindBy(xpath = PREPARED_COURSES_BUTTON)
    WebElement preparedCourseButton;
    private Map<String, Date> courseData = new HashMap<>();
    private CustomWebDriverWait customWebDriverWait = new CustomWebDriverWait(webDriver, 15L);

    public OtusMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PreparedCoursesPage openPreparedCoursesActions() {
        customWebDriverWait.waitLoadElement(cookieButton).click();
        actions
                .moveToElement(coursesButton)
                .click(preparedCourseButton)
                .build()
                .perform();
        return new PreparedCoursesPage(webDriver);
    }

    public void clickRandomCourseByNameOrPartName(String courseName) {
        if (customWebDriverWait.waitLoadElements(webDriver.findElements(By.xpath(SEARCH_COURSE_BY_NAME_LOCATOR.substring(0, SEARCH_COURSE_BY_NAME_LOCATOR.length() - 3) + courseName + SEARCH_COURSE_BY_NAME_LOCATOR.substring(SEARCH_COURSE_BY_NAME_LOCATOR.length() - 3))))) {
            List<WebElement> listCourseNames = webDriver.findElements(By.xpath(SEARCH_COURSE_BY_NAME_LOCATOR.substring(0, SEARCH_COURSE_BY_NAME_LOCATOR.length() - 3) + courseName + SEARCH_COURSE_BY_NAME_LOCATOR.substring(SEARCH_COURSE_BY_NAME_LOCATOR.length() - 3)));
            int courseNumber = new Random().nextInt(listCourseNames.size()) + 1;
            customWebDriverWait.waitLoadElement(webDriver.findElement(By.xpath(POPULAR_COURSES_URL_LOCATOR + "[" + courseNumber + "]"))).getAttribute("href");
            customWebDriverWait.waitLoadElement(webDriver.findElement(By.xpath(POPULAR_COURSES_URL_LOCATOR + "[" + courseNumber + "]"))).click();

        }
    }

    public String filterCourseNameFromMainPage(String courseName) {
        createMapCourseNameCourseDataStart(POPULAR_COURSES_DATES_LOCATOR, POPULAR_COURSES_NAMES_LOCATOR);
        return courseData
                .entrySet()
                .stream()
                .filter(k -> k.getKey().equals(courseName))
                .map(Map.Entry::getKey)
                .findAny().get();
    }

    public String getCourseByDate(String courseDate) {
        createMapCourseNameCourseDataStart(POPULAR_COURSES_DATES_LOCATOR, POPULAR_COURSES_NAMES_LOCATOR);
        Date startDate = DataParser.convertStringDateToCalendar(courseDate);
        return courseData
                .entrySet()
                .stream()
                .filter(v -> v.getValue().equals(startDate))
                .map(Map.Entry::getKey)
                .findAny().get();
    }

    public Map<String, Date> getAfterRecievedDate(String courseDate) {
        createMapCourseNameCourseDataStart(POPULAR_COURSES_DATES_LOCATOR, POPULAR_COURSES_NAMES_LOCATOR);
        Date startDate = DataParser.convertStringDateToCalendar(courseDate);
        return courseData
                .entrySet()
                .stream()
                .filter(v -> v.getValue().getTime() >= (startDate.getTime()))
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));

    }

    private Map<String, Date> createMapCourseNameCourseDataStart(String lstCourseDateLocator, String lstCourseNameLocator) {
        List<WebElement> listCourseNames = new ArrayList<>();
        if (customWebDriverWait.waitLoadElements(webDriver.findElements(By.xpath(lstCourseDateLocator)))) {
            listCourseNames = webDriver.findElements(By.xpath(lstCourseDateLocator));
        }
        listCourseNames.forEach(webElement -> {
            Date startDate = DataParser.convertStringDateToCalendar(webElement.getText());
            String courseName = webElement.findElement(By.xpath(lstCourseNameLocator)).getText();
            courseData.put(Optional.ofNullable(courseName).orElseThrow(RuntimeException::new), startDate);
        });
        return courseData;
    }
}