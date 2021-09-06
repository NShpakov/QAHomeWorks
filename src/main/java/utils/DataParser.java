package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataParser {
    private static final String[] RU_MONTH_PATTERNS_FROM_PAGE = {
            "января", "февраля", "марта", "апреля", "мая", "июня",
            "июля", "августа", "сентября", "октября", "ноября", "декабря"};
    private static final String[] ENG_MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private static Calendar courseDate = new GregorianCalendar();
    private static Date date;

    public static Calendar convertStringDateToCalendar(String dateFromPage) {
        for (int i = 0; i < ENG_MONTHS.length; i++) {
            if (dateFromPage.contains(RU_MONTH_PATTERNS_FROM_PAGE[i])) {
                dateFromPage = dateFromPage.replace(RU_MONTH_PATTERNS_FROM_PAGE[i], ENG_MONTHS[i])
                        .substring(2);
                break;
            }
        }
        try {
            date = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).parse(dateFromPage + " " + courseDate.getWeekYear());
            courseDate.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return courseDate;
    }
}