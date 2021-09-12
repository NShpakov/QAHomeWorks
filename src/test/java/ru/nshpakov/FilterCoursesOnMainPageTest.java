package ru.nshpakov;


import org.testng.annotations.Test;
import ru.nshpakov.pages.OtusMainPage;

import java.util.Date;

import static org.testng.AssertJUnit.assertEquals;


public class FilterCoursesOnMainPageTest extends BaseTest {
    private OtusMainPage otusMainPage;

    @Test(description = "Действия на вкладке курсы", enabled = true)
    public void actionsOnMainPage() {
        otusMainPage = new OtusMainPage(getDriver());
        otusMainPage.openCoursesActions();
    }

    @Test(description = "Кликаем по вкладке Курсы, проверяем подсветку", enabled = true)
    public void clickCourseMenu() {
        otusMainPage = new OtusMainPage(getDriver());
        otusMainPage.clickCourseButton();
    }

    @Test(description = "Фильтрация курсов по имени", enabled = true)
    public void getCourseName() {
        otusMainPage = new OtusMainPage(getDriver());
        String actualSpecialization = otusMainPage.filterCourseNameFromMainPage("Специализация Android-разработчик");
        String expectedSpecialization = "Специализация Android-разработчик";
        assertEquals(actualSpecialization, expectedSpecialization);
    }

    @Test(description = "Получаем самую последнюю дату начала курсов", enabled = true)
    public void getLastStartDate() {
        otusMainPage = new OtusMainPage(getDriver());
        Date lastDate = otusMainPage.getLastDate();
        String actualLastDate = lastDate.toString();
        String expectedLastDate = "Tue Oct 26 00:00:00 MSK 2021";
        String expectedCourseName = "Administrator Linux. Professional";
        String actualCourseName = otusMainPage.findCourseNameByDate(lastDate);
        assertEquals(actualLastDate, expectedLastDate);
        assertEquals(actualCourseName, expectedCourseName);
    }

    @Test(description = "Получаем самую первую дату начала курсов", enabled = true)
    public void getFirstStartDate() {
        otusMainPage = new OtusMainPage(getDriver());
        Date firstDate = otusMainPage.getFirstDate();
        String actualFirstDate = firstDate.toString();
        String actualCourseName = otusMainPage.findCourseNameByDate(firstDate);
        String expectedFirstDate = "Wed Sep 29 00:00:00 MSK 2021";
        String expectedCourseName = "DataOps Engineer";
        assertEquals(actualFirstDate, expectedFirstDate);
        assertEquals(expectedCourseName, actualCourseName);
    }
}