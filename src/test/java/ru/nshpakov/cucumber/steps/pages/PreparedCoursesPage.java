package ru.nshpakov.cucumber.steps.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.nshpakov.cucumber.steps.waits.CustomWebDriverWait;

import java.util.*;

public class PreparedCoursesPage extends BasePage {
    private final static String PREPARED_COURSES_PRICE_LOCATOR = ".//a[@class='js-stats lessons__new-item lessons__new-item_hovered']//div[@class='lessons__new-item-bottom']/div";
    private final static String PREPARED_COURSES_NAME_LOCATOR = "./ancestor::div[@class='lessons__new-item-container']/div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse']";
    private CustomWebDriverWait customWebDriverWait = new CustomWebDriverWait(webDriver, 15L);
    private Map<String, Integer> coursesInfo = new HashMap<>();

    public PreparedCoursesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    private Map<String, Integer> createMapCourseNameCoursePrice(String lstCoursePriceLocator, String lstCourseNameLocator) {
        List<WebElement> listCourseNames = new ArrayList<>();
        if (customWebDriverWait.waitLoadElements(webDriver.findElements(By.xpath(lstCoursePriceLocator)))) {
            listCourseNames = webDriver.findElements(By.xpath(lstCoursePriceLocator));
        }
        listCourseNames.forEach(webElement -> {
            coursesInfo.put(Optional.ofNullable(webElement.findElement(By.xpath(lstCourseNameLocator)).getText()).orElseThrow(RuntimeException::new), Optional.ofNullable(parsePrice(webElement)).orElseThrow(RuntimeException::new));
        });
        return coursesInfo;
    }

    private Integer parsePrice(WebElement element) {
        int formatedPrice = 0;
        try {
            formatedPrice = Integer.parseInt(element.getText().trim().substring(0, element.getText().length() - 1).replaceAll("\\s+", ""));
        } catch (NumberFormatException e) {
            new RuntimeException("Не удалось распознать цену курса");
        }
        return formatedPrice;
    }

    public Optional<Integer> getMaxPrice() {
        createMapCourseNameCoursePrice(PREPARED_COURSES_PRICE_LOCATOR, PREPARED_COURSES_NAME_LOCATOR);
        return coursesInfo.values().stream().reduce(Integer::max);
    }

    public Optional<Integer> getMinPrice() {
        createMapCourseNameCoursePrice(PREPARED_COURSES_PRICE_LOCATOR, PREPARED_COURSES_NAME_LOCATOR);
        return coursesInfo.values().stream().reduce(Integer::min);
    }

    public void printCourseInfo() {
        coursesInfo.entrySet()
                .stream()
                .forEach(System.out::println);
    }
}