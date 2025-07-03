package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Editprofilepage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(Editprofilepage.class);
    ExtentTest test;

    // ======= Locators =======
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");
    By editProfileBtn = By.xpath("//mat-card[6]//mat-card-content[1]//div[1]");

    // ======= Constructor =======
    public Editprofilepage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    // ======= Actions =======

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
            utilstest.captureScreenshot(driver, "loginFailedEditProfile");
        }
    }

    public void clickEditProfile() {
        try {
            WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(editProfileBtn));
            js.executeScript("arguments[0].scrollIntoView(true);", profile);
            js.executeScript("arguments[0].click();", profile);

            log.info("Edit profile button clicked");
            test.pass("Edit profile button clicked");

        } catch (Exception e) {
            log.error("Failed to click edit profile", e);
            test.fail("Edit profile click failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "editProfileFailed");
        }
    }
}
