package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * LoginPage class for OrangeHRM application login operations
 * Extends BasePage to leverage reusable Selenium methods
 * Handles login form interactions and dashboard verification
 */
public class LoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    // ==================== Locators ====================
    private final By txtUsername = By.name("username");
    private final By txtPassword = By.name("password");
    private final By btnLogin = By.cssSelector("button[type='submit']");
    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");

    // ==================== Constructor ====================

    /**
     * Constructor initializes LoginPage with WebDriver instance
     * 
     * @param driver WebDriver instance from DriverFactory
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        logger.debug("LoginPage initialized");
    }

    // ==================== Login Action Methods ====================

    /**
     * Enter username in the username field
     * Clears any existing text and enters the provided username
     * 
     * @param username Username to enter
     */
    public void enterUsername(String username) {
    	 logger.info("Entering username");
        type(txtUsername, username);
    }

    /**
     * Enter password in the password field
     * Clears any existing text and enters the provided password
     * 
     * @param password Password to enter
     */
    public void enterPassword(String password) {
        logger.info("Entering password");
        type(txtPassword, password);
    }

    /**
     * Click the login button
     * Waits for button to be clickable before clicking
     */
    public void clickLoginButton() {
        logger.info("Clicking Login button");
        click(btnLogin);
    }

    // ==================== Business Logic Methods ====================

    /**
     * Perform complete login flow with username and password
     * Encapsulates all login steps in a single business method
     * 
     * @param username Username for login
     * @param password Password for login
     */
    public void login(String username, String password) {
        logger.info("Performing login");
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Login steps completed");
    }

    // ==================== Verification Methods ====================

    /**
     * Verify if Dashboard page is displayed after login
     * Waits for dashboard header to be visible
     * 
     * @return true if dashboard header is visible and displayed, false otherwise
     */
    public boolean isDashboardDisplayed() {
        logger.info("Verifying Dashboard page is displayed");
        WebElement element = waitForVisibility(dashboardHeader);
        return element.isDisplayed();
    }

    /**
     * Get error message displayed on login page
     * Waits for error message to be visible before retrieving text
     * 
     * @return Error message text
     */
    public String getErrorMessage() {
        logger.info("Getting error message from login page");
        return getText(errorMessage);
    }
}
