package ru.nshpakov.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static final String PROPERTIES_FILE_NAME = "configuration.properties";
    private static Properties prop = new Properties();

    public ConfigReader() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" +PROPERTIES_FILE_NAME);
            {
                try {
                    System.out.println("Загрузка свойств из congiguration.poperties");
                    prop.load(inputStream);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Файл " + PROPERTIES_FILE_NAME + " не найден");
                }
            }
        } catch (IOException e) {
            new RuntimeException("Не удалось прочитать файл " + PROPERTIES_FILE_NAME);
        }

    }

    public static String getStringKeyProperty(String propertyName) {
        try {
            InputStream inputStream = ConfigReader.class.getResourceAsStream("/" +PROPERTIES_FILE_NAME);
            {
                try {
                    System.out.println("Загрузка свойств из congiguration.poperties");
                    prop.load(inputStream);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Файл " + PROPERTIES_FILE_NAME + " не найден");
                }
            }
        } catch (IOException e) {
            new RuntimeException("Не удалось прочитать файл " + PROPERTIES_FILE_NAME);
        }

        return prop.getProperty(propertyName);
    }
}