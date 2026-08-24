package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	 private final WebDriver driver;
	 private final WebDriverWait wait;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        this.wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	        }
	    private static final Logger logger =
	            LogManager.getLogger(LoginPage.class);
	    private By txtUsername = By.name("username");

	    private By txtPassword = By.name("password");

	    private By btnLogin = By.cssSelector("button[type='submit']");

	    private By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
	    private By errorMessage = By.cssSelector(".oxd-alert-content-text");
	    
	    public void enterUsername(String username) {
	    	logger.info("Entering username");

	    	
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsername));

	        driver.findElement(txtUsername).sendKeys(username);

	    }
	    public void enterPassword(String password) {
	    	 logger.info("Entering password");
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword));
	        driver.findElement(txtPassword).sendKeys(password);

	    }
	    public void clickLoginButton() {
	    	 logger.info("Clicking Login button");
	    	wait.until(ExpectedConditions.elementToBeClickable(btnLogin));

	        driver.findElement(btnLogin).click();

	    }
	    
	    public boolean isDashboardDisplayed() {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));

	        return driver.findElement(dashboardHeader).isDisplayed();

	    }
	    public String getErrorMessage() {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

	        return driver.findElement(errorMessage).getText();
	    }
}
