package ru.nshpakov.cucumber.steps.utils;

import ru.nshpakov.cucumber.steps.config.ConfigReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataParser {
    private static final String INPUT_FORMAT_DATE = ConfigReader.getStringKeyProperty("date_format");
    private static final String LOCALE = ConfigReader.getStringKeyProperty("locale").toUpperCase(Locale.ROOT);

    public static Date convertStringDateToCalendar(String dateFromPage) {
        Date inputDate = null;
        if (dateFromPage != null) {
            try {
                inputDate = new SimpleDateFormat(INPUT_FORMAT_DATE, new Locale(LOCALE))
                        .parse(dateFromPage.substring(2) + " " + new GregorianCalendar().getWeekYear());
            } catch (ParseException e) {
                new RuntimeException("Не удалось распарсить дату '" + dateFromPage + "' ");
            }
        } else new RuntimeException("Не удалось извлечь дату со страницы!");
        return inputDate;
    }
}