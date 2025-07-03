package Pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class VideoNonspokenpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(VideoNonspokenpage.class);
    ExtentTest test;

    // ====== Locators ======
    By emailInput = By.xpath("//input[@placeholder='Email address']");
    By passwordInput = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");
    By createBookingCard = By.xpath("//mat-card[2]//mat-card-content[1]//div[1]//div[2]//div[1]");
    By nonSpokenLangOption = By.xpath("//mat-card-title[normalize-space()='Non-Spoken Languages']");
    By videoType = By.xpath("//mat-label[normalize-space()='Video']");
    By addTimeBtn = By.xpath("//mat-label[@fxlayoutalign='space-evenly stretch']//mat-icon[normalize-space()='add_circle']");
    By calendarIcon = By.xpath("//*[name()='path' and contains(@d,'M19 3h-1V1')]");
    By bookingDate = By.xpath("//div[normalize-space()='2']");
    By minuteDropdown = By.xpath("//span[normalize-space()='MM']");
    By selectMinute = By.xpath("//span[@class='mat-option-text' and normalize-space()='00']");
    By saveTimeBtn = By.xpath("//span[normalize-space()='SAVE']");
    By languageIcon = By.xpath("//mat-list[1]//mat-list-item[5]//div[1]//mat-card[1]//mat-card-content[1]//div[1]//div[2]//mat-icon[1]");
    By contactNameInput = By.id("contactName");
    By contactNumberInput = By.id("contactNumber");
    By subjectIcon = By.xpath("//mat-list[3]//mat-list-item[3]//div[1]//mat-card[1]//mat-card-content[1]//div[1]//div[2]//mat-icon[1]");
    By jobDescriptionInput = By.id("jobDescription");
    By serviceUserInput = By.id("userName");
    By submitBooking = By.xpath("//mat-label[normalize-space()='SUBMIT']");

    // ====== Constructor ======
    public VideoNonspokenpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
    }

    // ===== Login =====
    public void login(String email, String password) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            log.info("Login successful");
            test.pass("Login successful");
        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "VideoNonspoken_LoginFail");
        }
    }

    // ===== Create Video Non-Spoken Booking =====
    public void createBooking(String contactName, String contactNumber, String jobDesc, String serviceUser) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createBookingCard)).click();
            wait.until(ExpectedConditions.elementToBeClickable(nonSpokenLangOption)).click();
            wait.until(ExpectedConditions.elementToBeClickable(videoType)).click();

            scrollClick(wait.until(ExpectedConditions.presenceOfElementLocated(addTimeBtn)));

            wait.until(ExpectedConditions.elementToBeClickable(calendarIcon)).click();
            wait.until(ExpectedConditions.elementToBeClickable(bookingDate)).click();

            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(minuteDropdown)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(selectMinute)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(saveTimeBtn)));

            Thread.sleep(1000);
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(languageIcon)));

            WebElement contactNameElem = wait.until(ExpectedConditions.elementToBeClickable(contactNameInput));
            scrollTo(contactNameElem);
            contactNameElem.clear();
            contactNameElem.sendKeys(contactName);

            WebElement contactNumElem = wait.until(ExpectedConditions.elementToBeClickable(contactNumberInput));
            scrollTo(contactNumElem);
            contactNumElem.clear();
            contactNumElem.sendKeys(contactNumber);

            Thread.sleep(1000);
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(subjectIcon)));

            WebElement jobDescElem = wait.until(ExpectedConditions.elementToBeClickable(jobDescriptionInput));
            scrollTo(jobDescElem);
            jobDescElem.clear();
            jobDescElem.sendKeys(jobDesc);

            WebElement serviceUserElem = wait.until(ExpectedConditions.elementToBeClickable(serviceUserInput));
            scrollTo(serviceUserElem);
            serviceUserElem.clear();
            serviceUserElem.sendKeys(serviceUser);

            js.executeScript("window.scrollTo(0, 0);");
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(submitBooking)));

            log.info("Video Non-Spoken Booking submitted successfully");
            test.pass("Booking submitted successfully");

        } catch (Exception e) {
            log.error("Booking creation failed", e);
            test.fail("Booking creation failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "VideoNonspoken_BookingFail");
        }
    }

    // ===== Utility Methods =====
    private void scrollClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }

    private void scrollTo(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}