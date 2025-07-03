package Pages;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class F2FSpokenpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions actions;
    Logger log = LogManager.getLogger(F2FSpokenpage.class);
    ExtentTest test;

    // ===== Locators =====
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
    By languageEditIcon = By.xpath("//mat-list[1]//mat-list-item[6]//mat-icon[1]");
    By contactNameInput = By.id("contactName");
    By contactNumberInput = By.id("contactNumber");
    By subjectEditIcon = By.xpath("//mat-list[3]//mat-list-item[3]//mat-icon[1]");
    By jobDescriptionInput = By.id("jobDescription");
    By serviceUserInput = By.id("userName");
    By submitBtn = By.xpath("//mat-label[normalize-space()='SUBMIT']");

    // ===== Constructor =====
    public F2FSpokenpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
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
            utilstest.captureScreenshot(driver, "loginF2Fspoken");
        }
    }

    // ===== Booking Step 1 =====
    public void createBooking() {
        try {
            clickElement(createBookingBtn);
            clickElement(spokenLanguageCard);
            clickElement(locationIcon);
            clickElement(addBookingTime);
            clickElement(calendarIcon);
            clickElement(selectDate);
            clickElement(minuteDropdown);
            clickElement(minuteOption00);
            clickElement(saveTimeBtn);

            log.info("Booking time added");
            test.pass("Booking time added");

        } catch (Exception e) {
            log.error("Create booking failed", e);
            test.fail("Create booking failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "createBookingF2Fspoken");
        }
    }

    // ===== Booking Step 2 =====
    public void fillBookingDetails(String contactName, String contactNumber, String jobDesc, String serviceUser) {
        try {
            clickElement(languageEditIcon);

            WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(contactNameInput));
            nameInput.clear();
            nameInput.sendKeys(contactName);

            WebElement numberInput = wait.until(ExpectedConditions.elementToBeClickable(contactNumberInput));
            numberInput.clear();
            numberInput.sendKeys(contactNumber);

            clickElement(subjectEditIcon);

            WebElement jobDescInput = wait.until(ExpectedConditions.elementToBeClickable(jobDescriptionInput));
            jobDescInput.clear();
            jobDescInput.sendKeys(jobDesc);

            WebElement userInput = wait.until(ExpectedConditions.elementToBeClickable(serviceUserInput));
            userInput.clear();
            userInput.sendKeys(serviceUser);

            js.executeScript("window.scrollTo(0, 0);");
            clickElement(submitBtn);

            log.info("F2F Spoken booking submitted");
            test.pass("F2F Spoken booking submitted");

        } catch (Exception e) {
            log.error("Submit booking failed", e);
            test.fail("Submit booking failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "fillDetailsF2Fspoken");
        }
    }

    // ===== Utility Click Method (Fixed) =====
    private void clickElement(By locator) {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", el);
            wait.until(ExpectedConditions.elementToBeClickable(el));

            Thread.sleep(300); // Let animations finish
            actions.moveToElement(el).pause(200).click().build().perform();

        } catch (Exception e) {
            log.error("Click failed for locator: " + locator.toString(), e);
            utilstest.captureScreenshot(driver, "clickFailed_" + locator.toString().replaceAll("[^a-zA-Z0-9]", ""));
            throw new RuntimeException("Click failed for: " + locator, e);
        }
    }
}
