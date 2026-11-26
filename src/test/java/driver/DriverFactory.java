package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    private static final Logger logger =
            LogManager.getLogger(DriverFactory.class);

    public static WebDriver initializeDriver(String browser) {

        WebDriver webDriver;

        switch (browser.toLowerCase()) {

            case "chrome":
                webDriver = new ChromeDriver();
                break;

            case "edge":
                webDriver = new EdgeDriver();
                break;

            case "firefox":
                webDriver = new FirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported browser : " + browser
                );
        }

        logger.info("Initializing browser: {}", browser);

        driver.set(webDriver);

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        logger.info("Browser initialized successfully");

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            logger.info("Closing browser");
            webDriver.quit();
            driver.remove();
        }
    }
}