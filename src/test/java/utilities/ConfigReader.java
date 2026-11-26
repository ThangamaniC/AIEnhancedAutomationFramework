package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    private static final Logger logger =
            LogManager.getLogger(ConfigReader.class);

    static {
        try (FileInputStream fis =
                     new FileInputStream("src/main/resources/config.properties")) {

            properties.load(fis);
            logger.info("Configuration file loaded successfully");

        } catch (IOException e) {

            logger.error("Failed to load config.properties", e);

            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Get browser from runtime parameter or config.properties
    public static String getBrowser() {

        String browser= System.getProperty(
                "browser",
                properties.getProperty("browser")
        );
        if (browser == null || browser.isBlank()) {
            throw new RuntimeException("Browser is not configured");
        }

        return browser;
    }

    // Get environment-specific URL
    // Default environment is QA
    public static String getUrl() {

        String environment = properties.getProperty("environment","QA");

        String url = properties.getProperty(environment);

        if (url == null || url.isBlank()) {
            throw new RuntimeException(
                    "URL not configured for environment: " + environment
            );
        }

        return url;
    }

    /*public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }

    public static String getTestDataPath() {
        return properties.getProperty("testdata.path");
    }*/
}