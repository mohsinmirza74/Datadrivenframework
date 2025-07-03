package Pages;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Futurebookingpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(Futurebookingpage.class);
    ExtentTest test;

    // ===== Locators =====
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");
    By futureBookingTab = By.xpath("//div[normalize-space()='Future']");

    // ===== Constructor =====
    public Futurebookingpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    // ===== Login Method =====
    public void loginToTLS(String email, String password) {
        try {
            WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailElem.clear();
            emailElem.sendKeys(email);

            WebElement passElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passElem.clear();
            passElem.sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            log.info("Logged in successfully");
            test.pass("Logged in successfully");

        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "loginTLSFuture");
        }
    }

    // ===== Click Future Booking Tab =====
    public void clickFutureBookingTab() {
        try {
            WebElement futureTab = wait.until(ExpectedConditions.elementToBeClickable(futureBookingTab));
            futureTab.click();

            log.info("Future Booking tab clicked");
            test.pass("Future Booking tab clicked");

        } catch (Exception e) {
            log.error("Failed to click Future Booking tab", e);
            test.fail("Click on Future Booking tab failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "futureBookingClick");
        }
    }

    // ===== Optional Placeholder Method =====
    public void viewFutureBookings() {
        // TODO: Add logic to verify future booking details (cards, status, etc.)
    }
}
