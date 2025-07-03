package Pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Videospokenpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(Videospokenpage.class);
    ExtentTest test;

    // === Locators ===
    By emailField = By.xpath("//input[@placeholder='Email address']");
    By passwordField = By.xpath("//input[@placeholder='Password']");
    By loginButton = By.xpath("//span[normalize-space()='Log in']");
    By createBookingBtn = By.xpath("//mat-card[2]//mat-card-content[1]//div[1]//div[2]//div[1]");
    By spokenLanguageCard = By.xpath("//mat-card-title[normalize-space()='Spoken Languages']");
    By locationIcon = By.xpath("//div[@fxlayoutalign='center center']//mat-icon[normalize-space()='circle']");
    By addBookingTime = By.xpath("//mat-label[@fxlayoutalign='space-evenly stretch']//mat-icon[normalize-space()='add_circle']");
    By calendarIcon = By.xpath("//button[@aria-label='Open calendar']//span[@class='mat-button-wrapper']//*[name()='svg']");
    By selectDate = By.xpath("//div[normalize-space()='2']");
    By minuteDropdown = By.xpath("//span[normalize-space()='MM']");
    By minuteOption00 = By.xpath("//span[@class='mat-option-text' and normalize-space()='00']");
    By saveTimeBtn = By.xpath("//span[normalize-space()='SAVE']");
    By languageEditIcon = By.xpath("//mat-list[1]//mat-list-item[6]//mat-icon");
    By contactNameInput = By.id("contactName");
    By contactNumberInput = By.id("contactNumber");
    By subjectEditIcon = By.xpath("//mat-list[3]//mat-list-item[3]//mat-icon");
    By jobDescriptionInput = By.id("jobDescription");
    By serviceUserInput = By.id("userName");
    By submitBtn = By.xpath("//mat-label[normalize-space()='SUBMIT']");

    // === Constructor ===
    public Videospokenpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
        this.test = test;
    }

    // === Login ===
    public void login(String email, String password) {
        try {
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailInput.clear();
            emailInput.sendKeys(email);

            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passwordInput.clear();
            passwordInput.sendKeys(password);

            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            log.info("Login successful");
            test.pass("Login successful");

        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "Login_Fail");
        }
    }

    // === Booking Creation ===
    public void createBooking() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createBookingBtn)).click();
            wait.until(ExpectedConditions.elementToBeClickable(spokenLanguageCard)).click();

            scrollClick(wait.until(ExpectedConditions.visibilityOfElementLocated(locationIcon)));
            scrollClick(wait.until(ExpectedConditions.presenceOfElementLocated(addBookingTime)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(calendarIcon)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(selectDate)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(minuteDropdown)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(minuteOption00)));
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(saveTimeBtn)));

            log.info("Booking time saved");
            test.pass("Booking time saved successfully");

        } catch (Exception e) {
            log.error("Booking creation failed", e);
            test.fail("Booking creation failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "BookingTime_Fail");
        }
    }

    // === Fill Booking Details ===
    public void fillBookingDetails(String contactName, String contactNumber, String jobDesc, String serviceUser) {
        try {
            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(languageEditIcon)));

            WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(contactNameInput));
            nameInput.clear();
            nameInput.sendKeys(contactName);

            WebElement numberInput = wait.until(ExpectedConditions.elementToBeClickable(contactNumberInput));
            numberInput.clear();
            numberInput.sendKeys(contactNumber);

            scrollClick(wait.until(ExpectedConditions.elementToBeClickable(subjectEditIcon)));

            WebElement jobDescInput = wait.until(ExpectedConditions.elementToBeClickable(jobDescriptionInput));
            jobDescInput.clear();
            jobDescInput.sendKeys(jobDesc);

            WebElement userInput = wait.until(ExpectedConditions.elementToBeClickable(serviceUserInput));
            userInput.clear();
            userInput.sendKeys(serviceUser);

            js.executeScript("window.scrollTo(0, 0);");
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

            log.info("Booking details submitted successfully");
            test.pass("Booking submitted successfully");

        } catch (Exception e) {
            log.error("Booking detail filling failed", e);
            test.fail("Detail filling failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "BookingDetails_Fail");
        }
    }

    // === Scroll and Click ===
    private void scrollClick(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
    }
}
