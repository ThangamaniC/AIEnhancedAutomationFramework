package utilities;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;

public class ScreenshotUtil {

    private static final ThreadLocal<String> screenshotPath = new ThreadLocal<>();
    private static final Logger logger =
            LogManager.getLogger(ScreenshotUtil.class);

    // Captures screenshot for Cucumber and stores the path for Extent
    public static void captureScreenshot(WebDriver driver, Scenario scenario) {

        try {

            TakesScreenshot ts = (TakesScreenshot) driver;

            File sourceFile = ts.getScreenshotAs(OutputType.FILE);

            String scenarioName =
                    scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_");

            String filePath =
                    "target/screenshots/"
                    + scenarioName
                    + "_"
                    + System.currentTimeMillis()
                    + ".png";

            File destinationFile = new File(filePath);

            FileUtils.copyFile(sourceFile, destinationFile);

            // Store screenshot path for Extent Report
            screenshotPath.set(destinationFile.getAbsolutePath());

            // Attach screenshot to Cucumber Report
            byte[] screenshotBytes =
                    FileUtils.readFileToByteArray(destinationFile);

            scenario.attach(
                    screenshotBytes,
                    "image/png",
                    "Failure Screenshot"
            );

        } catch (IOException e) {

        	logger.error("Unable to save screenshot", e);
            
			throw new RuntimeException(
					"Unable to save screenshot: " + e.getMessage(),
					e);
        }
    }

    // Returns the screenshot path to Extent Report
    public static String getScreenshotPath() {

        return screenshotPath.get();
    }
    public static void clearScreenshotPath() {
        screenshotPath.remove();
    }
}