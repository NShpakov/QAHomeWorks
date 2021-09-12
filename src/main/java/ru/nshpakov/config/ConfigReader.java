package ru.nshpakov.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties prop = new Properties();

    public ConfigReader() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" + Constants.PROPERTIES_FILE_NAME);
            {
                try {
                    System.out.println("Загрузка свойств из congiguration.poperties");
                    prop.load(inputStream);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Файл " + Constants.PROPERTIES_FILE_NAME + " не найден");
                }
            }
        } catch (IOException e) {
            new RuntimeException("Не удалось прочитать файл " + Constants.PROPERTIES_FILE_NAME);
        }

    }

    public String getStringKeyProperty(String propertyName) {
        return prop.getProperty(propertyName);
    }
}