package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Changepasswordpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(Changepasswordpage.class);
    ExtentTest test;

    // === Locators ===
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginBtn = By.xpath("//span[normalize-space()='Log in']");
    By changePasswordTab = By.xpath("//mat-label[normalize-space()='Change Password']");
    By newPasswordInput = By.xpath("//input[@name='password']");
    By confirmPasswordInput = By.xpath("//input[@name='confirmPassword']");
    By updatePasswordBtn = By.xpath("//span[@fxhide.lt-md='true']");

    // === Constructor ===
    public Changepasswordpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    // === Login Method ===
    public void login(String email, String password) {
        try {
            WebElement emailElem = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailElem.clear();
            emailElem.sendKeys(email);

            WebElement passElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passElem.clear();
            passElem.sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();

            log.info("Login successful Done");
            test.pass("Login successfully");

        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "loginFailed_ChangePassword");
        }
    }

    // === Change Password Method ===
    public void changePassword(String newPassword) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(changePasswordTab)).click();
            log.info("Change password tab opened");
            test.pass("Change password tab opened");

            WebElement newPassElem = wait.until(ExpectedConditions.elementToBeClickable(newPasswordInput));
            newPassElem.clear();
            newPassElem.sendKeys(newPassword);

            WebElement confirmPassElem = wait.until(ExpectedConditions.elementToBeClickable(confirmPasswordInput));
            confirmPassElem.clear();
            confirmPassElem.sendKeys(newPassword);

            wait.until(ExpectedConditions.elementToBeClickable(updatePasswordBtn)).click();

            log.info("Password updated successfully");
            test.pass("Password updated successfully");

        } catch (Exception e) {
            log.error("Failed to change password", e);
            test.fail("Change password failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "changePasswordFailed");
        }
    }
}
