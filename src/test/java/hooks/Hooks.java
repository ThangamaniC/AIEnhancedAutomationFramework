package hooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.ScreenshotUtil;

public class Hooks {
	private static final Logger logger =LogManager.getLogger(Hooks.class);


    @Before
 
    public void setUp() {
    	 logger.info("Starting test setup");
        DriverFactory.initializeDriver(ConfigReader.getBrowser());
        logger.info("Test setup completed");
       
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
        	 logger.error("Scenario failed: {}. Capturing screenshot...",scenario.getName());
            ScreenshotUtil.captureScreenshot(
                    DriverFactory.getDriver(),
                    scenario
            );
        }
        else {

            logger.info(
                    "Scenario passed: {}",
                    scenario.getName()
            );
        }

        DriverFactory.quitDriver();
        logger.info("Test teardown completed");
    }
    
}