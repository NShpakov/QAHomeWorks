package ru.nshpakov.config;

public class Constants {
    public static final String PROPERTIES_FILE_NAME = "configuration.properties";
    private static ConfigReader confReader = new ConfigReader();

    public static String getBaseUrl() {
        return confReader.getStringKeyProperty("url");
    }
    public static String getBaseBrowser() {
        return confReader.getStringKeyProperty("browser").toUpperCase();
    }
}