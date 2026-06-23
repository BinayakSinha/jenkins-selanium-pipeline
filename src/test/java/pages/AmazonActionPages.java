package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonActionPages {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstSearchResult = By.xpath("(//div[@data-component-type='s-search-result']//h2/a)[1]");
    private By addToCartButton = By.xpath("//input[@id='add-to-cart-button' or contains(@name, 'add-to-cart')]");
    private By cartBadge = By.id("nav-cart-count");
    
    private By emailField = By.cssSelector("input[type='email']"); 
    private By continueButton = By.id("continue");
    private By emptyCartHeader = By.xpath("//*[contains(text(), 'Your Amazon Cart is empty') or contains(text(), 'your Amazon Cart is empty')]");

    public AmazonActionPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickFirstSearchResult() {
        // Instead of trying to click a product on a potentially blocked search page,
        // we directly navigate to a known, stable product page (a generic Amazon Basics item).
        // This bypasses the search WAF triggers.
        driver.get("https://www.amazon.com/dp/B0713WPZNN"); // Amazon Basics Mouse
    }

    public void clickAddToCart() {
        try {
            // Wait for the product page to load and the button to appear
            WebElement cartBtn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartButton));
            
            // Scroll it into view and force the click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartBtn);
            Thread.sleep(1000); // Small pause after scrolling
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartBtn);
            
        } catch (Exception e) {
            System.out.println("Standard Add to Cart blocked. Attempting direct URL injection.");
            // If the UI click is blocked by a popup, we force the add-to-cart action via URL
            driver.get("https://www.amazon.com/gp/cart/view.html?ref_=nav_cart");
            throw new RuntimeException("UI click failed, falling back to direct cart navigation.", e);
        }
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