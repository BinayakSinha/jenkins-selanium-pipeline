package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StoreHomePage {
    // We removed the 'private WebDriver driver;' line
    private WebDriverWait wait;

    // Locators
    private By laptopsCategory = By.xpath("//a[contains(text(), 'Laptops')]");
    private By firstProductLink = By.cssSelector(".card-title a");
    private By cartNav = By.id("cartur");
    private By loginNav = By.id("login2");
    private By contactNav = By.xpath("//a[contains(text(), 'Contact')]");
    private By sampleLaptopCheck = By.xpath("//a[contains(text(), 'MacBook air')]");

    // The driver is still passed in here to set up the wait, but we don't save it forever
    public StoreHomePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectCategory(String category) {
        if(category.equalsIgnoreCase("Laptops")) {
            wait.until(ExpectedConditions.elementToBeClickable(laptopsCategory)).click();
        }
    }

    public boolean isLaptopDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(sampleLaptopCheck)).isDisplayed();
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(firstProductLink)).click();
    }

    public void clickCartNav() {
        wait.until(ExpectedConditions.elementToBeClickable(cartNav)).click();
    }

    public void clickLoginNav() {
        wait.until(ExpectedConditions.elementToBeClickable(loginNav)).click();
    }

    public void clickContactNav() {
         wait.until(ExpectedConditions.elementToBeClickable(contactNav)).click();
    }
}