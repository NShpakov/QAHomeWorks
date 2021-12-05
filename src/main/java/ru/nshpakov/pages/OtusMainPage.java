package ru.nshpakov.pages;

import ru.nshpakov.utils.DataParser;
import ru.nshpakov.waits.CustomWebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class OtusMainPage extends BasePage {
    private final static String POPULAR_COURSES_DATES_LOCATOR = ".//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following::div[@class='lessons'][1]/a/div/div[@class='lessons__new-item-bottom']/div[@class='lessons__new-item-start']";
    private final static String POPULAR_COURSES_NAMES_LOCATOR = "./ancestor::div[@class='lessons__new-item-container']/div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse']";
    private static final String COURSE_BUTTON = "(.//div[@class='header2-menu__item-wrapper']/p[text()=\"Курсы\"])[1]";
    private Actions actions = new Actions(webDriver);
    @FindBy(xpath = COURSE_BUTTON)
    WebElement coursesButton;
    private Map<String, Date> courseData = new HashMap<>();
    private CustomWebDriverWait customWebDriverWait = new CustomWebDriverWait(webDriver, 15L);

    public OtusMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openCoursesActions() {
        actions
                .moveToElement(customWebDriverWait.waitLoadElement(coursesButton))
                .sendKeys(Keys.ARROW_DOWN)
                .click()
                .build()
                .perform();
    }

    public void clickCourseButton() { customWebDriverWait.waitLoadElement(coursesButton).getAttribute("__selenideHighlighting");
        customWebDriverWait.waitLoadElement(coursesButton).click();
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

    private Map<String, Date> createMapCourseNameCourseDataStart(String lstCourseDateLocator, String lstCourseNameLocator) {
        List<WebElement> listCourseNames = new ArrayList<>();
        if(customWebDriverWait.waitLoadElements(webDriver.findElements(By.xpath(lstCourseDateLocator)))){
          listCourseNames = webDriver.findElements(By.xpath(lstCourseDateLocator));
        }
        listCourseNames.forEach(webElement -> {
            Date startDate = DataParser.convertStringDateToCalendar(webElement.getText());
            String courseName = webElement.findElement(By.xpath(lstCourseNameLocator)).getText();
            courseData.put(courseName, startDate);
        });
        return courseData;
    }

    public Date getLastDate() {
        createMapCourseNameCourseDataStart(POPULAR_COURSES_DATES_LOCATOR, POPULAR_COURSES_NAMES_LOCATOR);
        Date lastDate = courseData.values().stream().reduce(createMinDate(), (dateCoures1, dateCourse2) -> dateCoures1.getTime() > dateCourse2.getTime() ? dateCoures1 : dateCourse2);
        return lastDate;
    }

    public Date getFirstDate() {
        return createMapCourseNameCourseDataStart(POPULAR_COURSES_DATES_LOCATOR, POPULAR_COURSES_NAMES_LOCATOR)
                .values()
                .stream()
                .reduce(createMaxDate(), (dateCoures1, dateCourse2) -> dateCoures1.getTime() < dateCourse2.getTime() ? dateCoures1 : dateCourse2);
    }

    public String findCourseNameByDate(Date date) {
        return courseData
                .entrySet()
                .stream()
                .filter(v -> v.getValue().equals(date))
                .map(Map.Entry::getKey)
                .findAny().get();
    }

    private Date createMaxDate() {
        Calendar date = Calendar.getInstance();
        date.set(2100, 01, 01);
        return date.getTime();
    }

    private Date createMinDate() {
        Calendar date = Calendar.getInstance();
        date.set(2000, 01, 01);
        return date.getTime();
    }
}