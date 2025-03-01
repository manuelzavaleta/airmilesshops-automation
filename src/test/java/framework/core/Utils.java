package framework.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {
    private static Utils instance;
    private final Properties properties;

    private Utils() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/test/resources/page_config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public Properties getProperties() {
        return properties;
    }
}
