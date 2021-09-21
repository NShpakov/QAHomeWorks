package ru.nshpakov;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.nshpakov.pages.OtusMainPage;
import ru.nshpakov.pages.ProgrammingPage;
import java.util.Date;
import static org.testng.AssertJUnit.assertEquals;


public class FilterCoursesOnMainPageTest extends BaseTest {
    private OtusMainPage otusMainPage;
    private ProgrammingPage programmingPage;

    @BeforeMethod
    public void initPage() {
        otusMainPage = new OtusMainPage(getDriver());
    }

    @Test(description = "Действия на вкладке курсы", enabled = true)
    public void actionsOnMainPage() {
        otusMainPage.openCoursesActions();
    }

    @Test(description = "Фильтрация курсов по имени", enabled = true)
    public void getCourseName() {
        String actualSpecialization = otusMainPage.filterCourseNameFromMainPage("Highload Architect");
        String expectedSpecialization = "Highload Architect";
        assertEquals(expectedSpecialization, actualSpecialization);
    }

    @Test(description = "Получаем самую последнюю дату начала курсов", enabled = true)
    public void getLastStartDate() {
        Date lastDate = otusMainPage.getLastDate();
        String actualLastDate = lastDate.toString();
        String expectedLastDate = "Thu Sep 30 00:00:00 MSK 2021";
        String expectedCourseName = "Специализация Android-разработчик";
        String actualCourseName = otusMainPage.findCourseNameByDate(lastDate);
        assertEquals(expectedLastDate, actualLastDate);
        assertEquals(expectedCourseName, actualCourseName);
    }

    @Test(description = "Получаем самую первую дату начала курсов", enabled = true)
    public void getFirstStartDate() {
        Date firstDate = otusMainPage.getFirstDate();
        String actualFirstDate = firstDate.toString();
        String actualCourseName = otusMainPage.findCourseNameByDate(firstDate);
        String expectedFirstDate = "Tue Sep 28 00:00:00 MSK 2021";
        String expectedCourseName = "JavaScript Developer. Professional";
        assertEquals(expectedFirstDate, actualFirstDate);
        assertEquals(expectedCourseName, actualCourseName);
    }
}