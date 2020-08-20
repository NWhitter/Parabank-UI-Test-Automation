package org.parabank.test.automation.ui.helper;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private static final Properties properties = new Properties();

    private static final String PROP_FILE = "/application.properties";

    private static final String BASE_URL = "base.url";
    private static final String BROWSER_NAME = "browser.name";
    private static final String DB_PATH = "db.path";
    private static final String LOGIN_PATH = "login.path";

    static {
        try {
            properties.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
            properties.putAll(System.getProperties());      // Override properties with command line argument
        } catch (IOException ioe) {
            throw new RuntimeException("Property file not found");
        }
    }

    private static String loadProperty(String name) {
        return properties.getProperty(name);
    }

    public static String getBaseUrl() {
        return properties.getProperty(BASE_URL);
    }

    public static String getDatabasePageUrl() {
        return getBaseUrl() + properties.getProperty(DB_PATH);
    }

    public static String getLoginPageUrl() {
        return getBaseUrl() + properties.getProperty(LOGIN_PATH);
    }

    public static String getBrowserName() {
        return properties.getProperty(BROWSER_NAME);
    }
}
