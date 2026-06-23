package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AmazonHomePage;
import pages.AmazonActionPages;

import java.time.Duration;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonSteps {
    private WebDriver driver;
    private AmazonHomePage homePage;
    private AmazonActionPages actionPages;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        
        homePage = new AmazonHomePage(driver);
        actionPages = new AmazonActionPages(driver);
    }

    @Given("the user is on the Amazon homepage")
    public void navigateToAmazon() {
        driver.get("https://www.amazon.com/");
    }

    @When("the user searches for {string}")
    public void searchForProduct(String product) {
        homePage.searchForProduct(product);
    }

    @Then("the search results page should display results for {string}")
    public void verifySearchResults(String product) {
        assertTrue(driver.getTitle().contains(product));
    }

    @When("adds the first available item to the cart")
    public void addFirstItemToCart() {
        actionPages.clickFirstSearchResult();
        actionPages.clickAddToCart();
    }

    @Then("the cart badge count should increase")
    public void verifyCartBadgeIncrease() {
        int count = actionPages.getCartBadgeCount();
        assertTrue(count > 0);
    }

    @When("the user clicks on the Today's Deals menu")
    public void clickTodaysDeals() {
        homePage.clickTodaysDeals();
    }

    @Then("the Today's Deals page should load successfully")
    public void verifyTodaysDealsPage() {
        assertTrue(driver.getCurrentUrl().contains("goldbox") || driver.getTitle().contains("Deals"));
    }

    @When("the user clicks on the shopping cart icon")
    public void clickCartIcon() {
        homePage.clickCart();
    }

    @Then("the cart should display an empty cart message")
    public void verifyEmptyCart() {
        assertTrue(actionPages.isEmptyCartMessageDisplayed());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Kills the session and clears cache for the next scenario
        }
    }
}