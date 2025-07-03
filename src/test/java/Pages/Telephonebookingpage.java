package Pages;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;

import Utilities.utilstest;

public class Telephonebookingpage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Logger log = LogManager.getLogger(Telephonebookingpage.class);
    ExtentTest test;

    // ===== Locators =====
    By emailField         = By.xpath("//input[@placeholder='Email address']");
    By passwordField      = By.xpath("//input[@placeholder='Password']");
    By loginBtn           = By.xpath("//span[normalize-space()='Log in']");
    By createBooking      = By.xpath("//mat-card[2]//mat-card-content[1]//div[1]//div[2]//div[1]");
    By spokenLanguage     = By.xpath("//mat-card-title[normalize-space()='Spoken Languages']");
    By telephoneType      = By.xpath("//mat-label[normalize-space()='Telephone']");
    By addBookingTime     = By.xpath("//mat-label[@fxlayoutalign='space-evenly stretch']//mat-icon[normalize-space()='add_circle']");
    By calendarIcon       = By.xpath("//button[@aria-label='Open calendar']//*[name()='svg']");
    By dateSelect         = By.xpath("//div[normalize-space()='2']");
    By minuteDropdown     = By.xpath("//span[normalize-space()='MM']");
    By selectMinute00     = By.xpath("//span[@class='mat-option-text' and normalize-space()='00']");
    By saveTimeBtn        = By.xpath("//span[normalize-space()='SAVE']");
    By languageEdit       = By.xpath("//mat-list[1]//mat-list-item[6]//mat-icon[1]");
    By contactNameInput   = By.id("contactName");
    By contactNumberInput = By.id("contactNumber");
    By subjectEdit        = By.xpath("//mat-list[3]//mat-list-item[3]//mat-icon[1]");
    By jobDescriptionInput= By.id("jobDescription");
    By serviceUserInput   = By.id("userName");
    By submitBtn          = By.xpath("//mat-label[normalize-space()='SUBMIT']");

    public Telephonebookingpage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test   = test;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js     = (JavascriptExecutor) driver;
    }

    public void login(String email, String password) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
            log.info("Login successful");
            test.pass("Login successful");
        } catch (Exception e) {
            log.error("Login failed", e);
            test.fail("Login failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "TelephoneLoginFailure");
        }
    }

    public void createTelephoneSpokenBooking() {
        try {
            clickScrolled(createBooking);
            clickScrolled(spokenLanguage);
            clickScrolled(telephoneType);

            clickScrolled(addBookingTime);
            clickScrolled(calendarIcon);
            clickScrolled(dateSelect);
            clickScrolled(minuteDropdown);
            clickScrolled(selectMinute00);
            clickScrolled(saveTimeBtn);

            log.info("Booking time saved");
            test.pass("Telephone booking time saved");
        } catch (Exception e) {
            log.error("Booking creation failed", e);
            test.fail("Booking creation failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "TelephoneBookingCreationFailure");
        }
    }

    public void fillBookingForm(String name, String number, String jobDesc, String serviceUser) {
        try {
            clickScrolled(languageEdit);

            WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(contactNameInput));
            nameField.clear(); nameField.sendKeys(name);

            WebElement numberField = wait.until(ExpectedConditions.elementToBeClickable(contactNumberInput));
            numberField.clear(); numberField.sendKeys(number);

            clickScrolled(subjectEdit);

            WebElement jobField = wait.until(ExpectedConditions.elementToBeClickable(jobDescriptionInput));
            jobField.clear(); jobField.sendKeys(jobDesc);

            WebElement userField = wait.until(ExpectedConditions.elementToBeClickable(serviceUserInput));
            userField.clear(); userField.sendKeys(serviceUser);

            js.executeScript("window.scrollTo(0, 0);");
            wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

            log.info("Telephone spoken booking created");
            test.pass("Telephone spoken booking submitted");
        } catch (Exception e) {
            log.error("Booking form fill failed", e);
            test.fail("Booking form failed: " + e.getMessage());
            utilstest.captureScreenshot(driver, "TelephoneFormFailure");
        }
    }

    /** Scrolls the element into center view, waits briefly, then clicks natively. */
    private void clickScrolled(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        el.click();
    }
}
