package parallel.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import parallel.pageobjects.SearchPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class SearchSteps {
    private WebDriver driver;
    private SearchPage searchPage;
    private DesiredCapabilities caps;


    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
        String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
        String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.browserstack.com/wd/hub";
        caps = new DesiredCapabilities();
        String device = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("device");
        caps.setCapability("device", device);
        caps.setCapability("browserstack.idleTimeout", 20);
        caps.setCapability("project", "browserstack-cucumberbdd");
        caps.setCapability("build", "cucumber-testng-level");
        caps.setCapability("name", scenario.getName()+" on "+device);
        driver = new RemoteWebDriver(new URL(URL), caps);
        searchPage = new SearchPage(driver);

    }



    @Given("I am on the website {string}")
    public void I_am_on_the_website(String url) {
        driver.get(url);
    }


    @When("I submit the search term {string}")
    public void I_submit_the_search_term(String searchTerm) throws InterruptedException {
        searchPage.enterSearchTerm(searchTerm);
        searchPage.submitSearch();
        Thread.sleep(2000);
    }

    @Then("the page title should be {string}")
    public void I_should_see_pagetitle(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle());
        //assertEquals(driver.getTitle(), driver.getTitle());
        driver.quit();
    }

}
