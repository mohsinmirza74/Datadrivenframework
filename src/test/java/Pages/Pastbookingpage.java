package Pages;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Pastbookingpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(Pastbookingpage.class);
    ExtentTest test;

    // ===== Locators =====
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");
    By pastTab = By.xpath("//div[normalize-space()='Past']");
    By pageItemDropdown = By.xpath("//div[@id='mat-select-value-5']");
    By select25Item = By.xpath("//span[normalize-space()='25']");

    // ===== Constructor =====
    public Pastbookingpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    // ===== Login =====
    public void login(String email, String password) {
        try {
            WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailElem.clear();
            emailElem.sendKeys(email);

            WebElement passElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passElem.clear();
            passElem.sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            log.info("Login successful");
            test.pass("Login successful");

        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "PastBookingLoginFailure");
        }
    }

    // ===== Open Past Bookings Tab =====
    public void openPastBookings() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(pastTab)).click();
            log.info("Clicked on Past bookings tab");
            test.pass("Past bookings tab opened");

        } catch (Exception e) {
            log.error("Failed to open Past bookings tab", e);
            test.fail("Past bookings tab failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "PastTabClickFailure");
        }
    }

    // ===== Scroll and Select 25 Items per Page =====
    public void scrollAndSelectItemsPerPage() {
        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            log.info("Scrolled to bottom of the page");
            test.info("Scrolled successfully");

            wait.until(ExpectedConditions.elementToBeClickable(pageItemDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(select25Item)).click();

            log.info("Item count changed to 25");
            test.pass("Item count set to 25");

        } catch (Exception e) {
            log.error("Failed to scroll or change item count", e);
            test.fail("Scrolling/item change failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "PastScrollOrItemCountFailure");
        }
    }
}

