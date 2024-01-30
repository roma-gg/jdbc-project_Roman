package cydeo.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties = new Properties();

    static {
        FileInputStream file;
        try {
            file = new FileInputStream("configuration.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void addProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
