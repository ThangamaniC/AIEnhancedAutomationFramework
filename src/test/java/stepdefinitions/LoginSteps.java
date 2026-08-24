package stepdefinitions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.testng.Assert;

import driver.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utilities.ConfigReader;
	
public class LoginSteps {

    private LoginPage loginPage;
    private static final Logger logger =
            LogManager.getLogger(LoginSteps.class);
 
     @Given("User launches the OrangeHRM application")
     public void user_launches_the_orangehrm_application() {

    	 logger.info("Launching OrangeHRM application");
    	 DriverFactory.getDriver().get(ConfigReader.getUrl());

    	 loginPage = new LoginPage(DriverFactory.getDriver());        
    	 

}
    @When("User enters username {string}")
    public void user_enters_username(String username) {

    	logger.info("User enters username");
        loginPage.enterUsername(username);
    }

    @When("User enters password {string}")
    public void user_enters_password(String password) {
    	  logger.info("User enters password");

        loginPage.enterPassword(password);
    }

    @When("User clicks on Login button")
    public void user_clicks_on_login_button() {

        loginPage.clickLoginButton();
    }

    @Then("User should be navigated to the Dashboard page")
    public void user_should_be_navigated_to_the_dashboard_page() {
    	logger.info("Verifying Dashboard page");

        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard page is not displayed after login");
    }
    
 
    @Then("User should see an error message")
    public void user_should_see_an_error_message() {
    	 logger.info("Verifying invalid login error message");

		Assert.assertEquals(loginPage.getErrorMessage(),"Invalid credentials");
	}
    
}