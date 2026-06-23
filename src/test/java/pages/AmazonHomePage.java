package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox = By.id("twotabsearchtextbox");
    private By todaysDealsLink = By.xpath("//a[contains(@href, 'goldbox')]");
    private By signInAccountList = By.id("nav-link-accountList");
    private By cartIcon = By.id("nav-cart");

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchForProduct(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys(product, Keys.ENTER);
    }

    public void clickTodaysDeals() {
        WebElement dealsLink = wait.until(ExpectedConditions.presenceOfElementLocated(todaysDealsLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dealsLink);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInAccountList)).click();
    }

    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
}