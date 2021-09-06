package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String readPropertyByKey(String propertyName) {
        String pr = propertyName;
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/configuration.properties");
            property.load(fis);
        } catch (IOException e) {
            System.err.println("File doesn't found");
        }
        return property.getProperty(pr);
    }
}
