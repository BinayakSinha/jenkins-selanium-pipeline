package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StoreActionPages {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By addToCartButton = By.xpath("//a[contains(text(), 'Add to cart')]");
    private By totalElement = By.id("totalp");
    
    // Login Modal Locators
    private By loginUsernameField = By.id("loginusername");
    private By loginPasswordField = By.id("loginpassword");
    private By loginSubmitButton = By.xpath("//button[contains(text(), 'Log in')]");

    // Contact Modal Locators
    private By contactEmailField = By.id("recipient-email");
    private By contactNameField = By.id("recipient-name");
    private By contactMessageField = By.id("message-text");
    private By contactSubmitButton = By.xpath("//button[contains(text(), 'Send message')]");

    public StoreActionPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public boolean isAlertPresentAndAccept() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTotalCalculated() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalElement)).isDisplayed();
    }

    public void attemptLogin(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginUsernameField)).sendKeys(username);
        driver.findElement(loginPasswordField).sendKeys(password);
        driver.findElement(loginSubmitButton).click();
    }

    public void fillContactForm(String email, String name, String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmailField)).sendKeys(email);
        driver.findElement(contactNameField).sendKeys(name);
        driver.findElement(contactMessageField).sendKeys(message);
        driver.findElement(contactSubmitButton).click();
    }
}