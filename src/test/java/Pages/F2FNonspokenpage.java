package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class F2FNonspokenpage {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://tlsvista2.autosmarttech.com/");
        System.out.println("Page opened successfully");

        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Email address']")))
            .sendKeys("mirzamohsinlatif16@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Password']")))
            .sendKeys("Pakistan@1");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Log in']")))
            .click();
        System.out.println("Login successful");

        // Create booking
        scrollAndClick(wait, js, driver.findElement(By.xpath(
            "//mat-card[2]//mat-card-content[1]//div[1]//div[2]//div[1]")));
        System.out.println("Create booking clicked");

        // Non‑Spoken Language
        scrollAndClick(wait, js, driver.findElement(By.xpath(
            "//mat-card-title[normalize-space()='Non-Spoken Languages']")));
        System.out.println("Non‑Spoken Languages clicked");

        // Location click
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@fxlayoutalign='center center']//mat-icon[normalize-space()='circle']"))));
        System.out.println("Location clicked");

        // Add new booking time
        WebElement newBookingTimeBtn = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//mat-label[@fxlayoutalign='space-evenly stretch']//mat-icon[normalize-space()='add_circle']")));
        scrollAndClick(wait, js, newBookingTimeBtn);
        System.out.println("New booking time added");

        // Booking calendar
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@aria-label='Open calendar']//*[name()='svg']"))));
        System.out.println("Calendar clicked");

        // Select date
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[normalize-space()='2']"))));
        System.out.println("Date selected");

        // Click MM dropdown
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[normalize-space()='MM']"))));
        System.out.println("Minute dropdown clicked");

        // Select 00
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[@class='mat-option-text' and normalize-space()='00']"))));
        System.out.println("Minute 00 selected");

        // Save time
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[normalize-space()='SAVE']"))));
        System.out.println("Save clicked");

        // Language icon (edit pencil)
        Thread.sleep(500);
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//mat-list[1]//mat-list-item[5]//mat-icon"))));
        System.out.println("Language edit clicked");

        // Contact name
        WebElement contactName = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("contactName")));
        scrollIntoView(js, contactName);
        contactName.clear();
        contactName.sendKeys("Mohsin Latif");
        System.out.println("Contact name entered");

        // Contact number
        WebElement contactNumber = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("contactNumber")));
        scrollIntoView(js, contactNumber);
        contactNumber.clear();
        contactNumber.sendKeys("03351180865");
        System.out.println("Contact number entered");

        // Subject icon
        Thread.sleep(500);
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//mat-list[3]//mat-list-item[3]//mat-icon"))));
        System.out.println("Subject edit clicked");

        // Job description
        WebElement jobDescription = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("jobDescription")));
        scrollIntoView(js, jobDescription);
        jobDescription.clear();
        jobDescription.sendKeys("Quality assurance");
        System.out.println("Job description entered");

        // Service user
        WebElement serviceUser = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("userName")));
        scrollIntoView(js, serviceUser);
        serviceUser.clear();
        serviceUser.sendKeys("Mohsin");
        System.out.println("Service user entered");

        // Submit booking
        Thread.sleep(500);
        scrollAndClick(wait, js, wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//mat-label[normalize-space()='SUBMIT']"))));
        System.out.println("Booking submitted successfully");

        driver.quit();
    }

    /**
     * Scrolls element into view, pauses briefly, then uses native click().
     */
    private static void scrollAndClick(WebDriverWait wait, JavascriptExecutor js, WebElement el) {
        scrollIntoView(js, el);
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        el.click();
    }

    private static void scrollIntoView(JavascriptExecutor js, WebElement el) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

	public void createBooking() {
		// TODO Auto-generated method stub
		
	}

	public void fillBookingDetails(String string, String string2, String string3, String string4) {
		// TODO Auto-generated method stub
		
	}
}
