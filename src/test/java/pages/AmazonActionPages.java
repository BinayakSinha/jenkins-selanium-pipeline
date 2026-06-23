package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonActionPages {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstSearchResult = By.xpath("(//div[@data-component-type='s-search-result']//a[contains(@class, 'a-link-normal')])[1]");
    private By addToCartButton = By.xpath("//*[@id='add-to-cart-button' or @name='submit.add-to-cart']");
    private By cartBadge = By.id("nav-cart-count");
    
    private By emailField = By.cssSelector("input[type='email']"); 
    private By continueButton = By.id("continue");
    private By emptyCartHeader = By.xpath("//*[contains(text(), 'Your Amazon Cart is empty') or contains(text(), 'your Amazon Cart is empty')]");

    public AmazonActionPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickFirstSearchResult() {
        // Wait for the results to fully render
        try {
            Thread.sleep(1500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        wait.until(ExpectedConditions.elementToBeClickable(firstSearchResult)).click();
    }


    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public int getCartBadgeCount() {
        String countText = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
        return Integer.parseInt(countText);
    }

    public void enterEmailAndContinue(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        
        // Clear the field first just in case
        emailInput.clear();
        
        // Type the email slowly, character by character
        for (char c : email.toCharArray()) {
            emailInput.sendKeys(String.valueOf(c));
            try {
                // Sleep for 50-100ms between keystrokes
                Thread.sleep(75); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Add a slight pause before clicking the continue button
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        driver.findElement(continueButton).click();
    }

    

    public boolean isEmptyCartMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartHeader)).isDisplayed();
    }
}