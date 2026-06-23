package steps;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.StoreHomePage;
import pages.StoreActionPages;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreSteps {
    private WebDriver driver;
    private StoreHomePage homePage;
    private StoreActionPages actionPages;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080"); 

        driver = new ChromeDriver(options);
        
        homePage = new StoreHomePage(driver);
        actionPages = new StoreActionPages(driver);
    }

    @Given("the user is on the store homepage")
    public void navigateToStore() {
        driver.get("https://www.demoblaze.com/index.html");
    }

    @When("the user clicks on the {string} category")
    public void filterCategory(String category) {
        homePage.selectCategory(category);
    }

    @Then("the product list should update to show laptops")
    public void verifyCategoryUpdate() {
        assertTrue(homePage.isLaptopDisplayed());
    }

    @When("the user clicks on the first available product")
    public void clickFirstProduct() {
        homePage.clickFirstProduct();
    }

    @When("adds the item to the cart")
    public void addItemToCart() {
        actionPages.clickAddToCart();
    }

    @Then("a confirmation alert should appear")
    public void verifyAlertAppears() {
        assertTrue(actionPages.isAlertPresentAndAccept());
    }

    @When("the user navigates to the Cart page")
    public void navigateToCart() {
        homePage.clickCartNav();
    }

    @Then("the cart total should be calculated successfully")
    public void verifyCartTotal() {
        assertTrue(actionPages.isTotalCalculated());
    }

    @When("the user navigates to the login modal")
    public void openLoginModal() {
        homePage.clickLoginNav();
    }

    @When("enters the username {string} and password {string}")
    public void submitInvalidLogin(String username, String password) {
        actionPages.attemptLogin(username, password);
    }

    @Then("an alert should indicate the user does not exist")
    public void verifyLoginErrorAlert() {
        assertTrue(actionPages.isAlertPresentAndAccept());
    }

    @When("the user opens the contact message form")
    public void openContactForm() {
        homePage.clickContactNav();
    }

    @When("fills out the message details")
    public void submitContactForm() {
        actionPages.fillContactForm("test@email.com", "Test Name", "This is a test message.");
    }

    @Then("a confirmation alert should acknowledge the message")
    public void verifyContactAlert() {
         assertTrue(actionPages.isAlertPresentAndAccept());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}