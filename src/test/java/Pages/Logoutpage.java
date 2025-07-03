package Pages;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Logoutpage {

    WebDriver driver;
    WebDriverWait wait;
    Logger log = LogManager.getLogger(Logoutpage.class);
    JavascriptExecutor js;
    ExtentTest test;

    // ===== Locators =====
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");
    By logoutIcon = By.xpath("//mat-icon[normalize-space()='logout']");

    // ===== Constructor =====
    public Logoutpage(WebDriver driver, ExtentTest test) {
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

            log.info("Logged in successfully with email: " + email);
            test.pass("Login successful");

        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "Logout_LoginFailure");
        }
    }

    // ===== Logout Method =====
    public void logoutFromTLS() {
        try {
            WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutIcon));
            logout.click();

            log.info("Logout successful");
            test.pass("Logout successful");

        } catch (Exception e) {
            log.error("Logout failed", e);
            test.fail("Logout failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "Logout_Failure");
        }
    }


	public void logout() {
		// TODO Auto-generated method stub
		
	}

	

}
