package Pages;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Loginpage {

    WebDriver driver;
    WebDriverWait wait;
    Logger log = LogManager.getLogger(Loginpage.class);
    JavascriptExecutor js;
    ExtentTest test;

    // ===== Locators =====
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");

    // ===== Constructor =====
    public Loginpage(WebDriver driver, ExtentTest test) {
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

            WebElement passwordElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passwordElem.clear();
            passwordElem.sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            log.info("Login successful with email: " + email);
            test.pass("Login successful with email: " + email);

        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "loginFailure");
        }
    }
}
