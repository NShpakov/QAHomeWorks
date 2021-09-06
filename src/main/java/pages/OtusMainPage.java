package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataParser;
import waits.CustomWaitClass;
import java.util.*;
import java.util.stream.Collectors;

public class OtusMainPage extends BasePage {

    @FindBy(xpath = "(.//div[@class='header2-menu__item-wrapper']/p[text()=\"Курсы\"])[1]")
    WebElement coursesButton;
    Map<String, Date> courseData = new HashMap<>();
    public OtusMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Actions setActionsBuilder(WebDriver webDriver) {
        Actions builder = new Actions(webDriver);
        return builder;
    }

    public void waitCoursesElements() {
        new WebDriverWait(webDriver, 10).until(CustomWaitClass.waitRefresh(".//div[@class='header2-menu__item-wrapper']/p[text()=\"Курсы\"]"));
    }

    public void openCoursesActions() {
        Actions builder = setActionsBuilder(webDriver);
        builder.click(coursesButton)
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .pause(500)
                .sendKeys(Keys.ARROW_DOWN)
                .pause(500).
                sendKeys(Keys.ARROW_DOWN)
                .pause(500).
                sendKeys(Keys.ARROW_UP)
                .pause(500).
                click()
                .pause(500)
                .build()
                .perform();
    }

    public void clickCourseButton() {
        new WebDriverWait(webDriver, 10).until(CustomWaitClass.waitRefresh(".//div[@class='header2-menu__item-wrapper']/p[text()=\"Курсы\"]"));
        coursesButton.click();
    }

    public String getCourseNameFromMainPage(String courseName) {
        return filterListCoursesByName(courseName).get(0).getText();
    }

    //Собираем имена курсов на главной странице из раздела Специализации
    private List<WebElement> filterListCoursesByName(String courseName) {
        List<WebElement> lisCourses = webDriver.findElements(By.xpath(".//div[@class=\"subtitle-new\" and text()='Специализации']/following::div[@class='lessons'][1]/a/div/div[4]"));
        return lisCourses.stream()
                .filter(Objects::nonNull)
                .filter(webElement -> webElement
                        .getText().trim()
                        .equals(courseName)).collect(Collectors.toList());
    }
    //получаем список популярных курсов
    public Map<String, Date> createMapCourseNameCourseDataStart() {
        List<WebElement> listCourseNames = webDriver.findElements(By.xpath(".//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following::div[@class='lessons'][1]/a/div/div[4]"));
        for (int i = 1; i <= listCourseNames.size(); i++) {
            Date startDate = DataParser.convertStringDateToCalendar(webDriver.findElement(By.xpath("(.//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following::div[@class='lessons'][1]/a/div/div[@class='lessons__new-item-bottom']/div[@class='lessons__new-item-start'])[" + i + "]")).getText()).getTime();
            String courseName = webDriver.findElement(By.xpath("(.//div[@class=\"subtitle-new\" and text()='Популярные курсы']/following::div[@class='lessons'][1]/a/div/div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse'])[" + i + "]")).getText();
            courseData.put(courseName, startDate);
        }
        return courseData;
    }

    public Date getLastDate() {
        Date LastDate = null;
        Date today = new Date();
        today.getTime();
        LastDate = courseData.values().stream().reduce(today, (dateCoures1, dateCourse2) -> dateCourse2.compareTo(dateCoures1) < 0 ? dateCoures1 : dateCourse2);
        return LastDate;
    }

    public Date getFirstDate() {
        Date firstDate = null;
        Date today = new Date();
        today.getTime();
        firstDate = courseData.values().stream().reduce(today, (dateCoures1, dateCourse2) -> dateCourse2.compareTo(dateCoures1) >= 0 ? dateCoures1 : dateCourse2);
        return firstDate;
    }

    public String findCourseNameByDate(Date date) {
        return courseData
                .entrySet()
                .stream()
                .filter(v -> v.getValue().equals(date))
                .map(Map.Entry::getKey)
                .findAny().get();
    }
}