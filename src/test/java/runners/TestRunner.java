package runners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;
import utilities.ExtentReportUtilityListener;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

	@CucumberOptions(
	        features = "src/test/resources/features",
	        glue = {"stepdefinitions", "hooks"},
	        tags="@sanity",
	        		plugin = {
	        			    "pretty",
	        			    "html:target/cucumber-reports/cucumber.html",
	        			    "json:target/cucumber-reports/cucumber.json",
	        			   
	        			},
	        monochrome = true
	)
	
	@Listeners(ExtentReportUtilityListener.class)
	public class TestRunner extends AbstractTestNGCucumberTests {

		/*@BeforeClass(alwaysRun = true)
	    @Parameters("browser")
	    public void setUpClass(String browser) {
	        ConfigReader.setBrowser(browser);
	    }*/

	    @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }

	}

