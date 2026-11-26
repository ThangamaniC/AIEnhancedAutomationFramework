package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage class containing reusable Selenium operations
 * All page objects should extend this class to leverage common methods
 * 
 * Features:
 * - Explicit waits for element synchronization
 * - Reusable methods for common Selenium operations
 * - Comprehensive logging for debugging
 * - No PageFactory, implicit waits, or Thread.sleep
 * 
 * Usage:
 * public class LoginPage extends BasePage {
 *     public LoginPage(WebDriver driver) {
 *         super(driver);
 *     }
 * }
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    
    private static final int TIMEOUT_SECONDS = 10;

    /**
     * Constructor initializes WebDriver and WebDriverWait
     * WebDriver initialization is handled by Hooks and DriverFactory
     * 
     * @param driver WebDriver instance from DriverFactory
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        logger.debug("BasePage initialized with timeout: {} seconds", TIMEOUT_SECONDS);
    }

    // ==================== Explicit Wait Methods ====================

    /**
     * Wait until element is visible in DOM and displayed
     * 
     * @param locator By locator of the element
     * @return WebElement once it becomes visible
     */
    protected WebElement waitForVisibility(By locator) {
        logger.debug("Waiting for element visibility: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait until element is clickable
     * Element must be visible and enabled
     * 
     * @param locator By locator of the element
     * @return WebElement once it becomes clickable
     */
    protected WebElement waitForClickability(By locator) {
        logger.debug("Waiting for element to be clickable: {}", locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait until element is present in DOM
     * Element may not be visible or displayed
     * 
     * @param locator By locator of the element
     * @return WebElement once it is present in DOM
     */
    protected WebElement waitForPresence(By locator) {
        logger.debug("Waiting for element presence in DOM: {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ==================== Element Interaction Methods ====================

    
    /**
     * Clear and type text into an element
     * Clears any existing text before typing new text
     * Waits for element visibility before interaction
     * 
     * @param locator By locator of the element
     * @param text Text to be typed
     */
    protected void type(By locator, String text) {
        logger.info("Typing text into element: {}", locator);
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
        logger.debug("Text entered successfully");
    }

    /**
     * Click on an element
     * Waits for element to be clickable before clicking
     * 
     * @param locator By locator of the element
     */
    protected void click(By locator) {
        logger.info("Clicking on element: {}", locator);
        WebElement element = waitForClickability(locator);
        element.click();
        logger.debug("Element clicked successfully");
    }

    /**
     * Get text content from an element
     * Waits for element visibility before getting text
     * 
     * @param locator By locator of the element
     * @return Text content of the element
     */
    protected String getText(By locator) {
        logger.info("Getting text from element: {}", locator);
        WebElement element = waitForVisibility(locator);
        String text = element.getText();
        logger.debug("Text retrieved: {}", text);
        return text;
    }

      

    /**
     * Clear text from an input field
     * Waits for element visibility before clearing
     * 
     * @param locator By locator of the element
     */
    protected void clearField(By locator) {
        logger.info("Clearing text field: {}", locator);
        WebElement element = waitForVisibility(locator);
        element.clear();
        logger.debug("Text field cleared successfully");
    }
}
