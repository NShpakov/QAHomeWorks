package ru.nshpakov.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataParser {
    private static final String INPUT_FORMAT_DATE = "dd MMMM yyyy";
    private static final String LOCALE = "RU";

    public static Date convertStringDateToCalendar(String dateFromPage) {
        Date inputDate = null;
        if (dateFromPage != null) {
            try {
                inputDate = new SimpleDateFormat(INPUT_FORMAT_DATE, new Locale(LOCALE))
                        .parse(dateFromPage.substring(2) + " " + new GregorianCalendar().getWeekYear());
            } catch (ParseException e) {
                new RuntimeException("Не удалось распарсить дату '" + dateFromPage + "' ");
            }
        } else new RuntimeException("Не удалосб извлечь дату со страницы!");
        return inputDate;
    }
}