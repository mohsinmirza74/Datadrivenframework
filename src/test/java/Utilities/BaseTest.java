package Utilities;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.*;

import org.apache.logging.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Pages.Loginpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(
            System.getProperty("user.dir") + "/Reports/TLSAutomationReport.html"
        );
        reporter.config().setDocumentTitle("TLS Customer Booking Report");
        reporter.config().setReportName("TLS Automation Execution");
        reporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Mohsin Latif");
        log.info("Extent report initialized");
    }

    @BeforeMethod
    public void setupBrowser(Method method) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tlsvista2.autosmarttech.com/");
        log.info("Browser launched and navigated to TLS");

        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);  // Thread-safe
        Loginpage lp = new Loginpage(driver, getTest());
        lp.loginToTLS("mirzamohsinlatif16@gmail.com", "Pakistan@1");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = captureScreenshot(driver, result.getName());
            getTest().fail("Test Failed").addScreenCaptureFromPath(screenshotPath);
            log.error("❌ Test Failed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            getTest().pass("✅ Test Passed: " + result.getName());
            log.info("✅ Test Passed: " + result.getName());
        } else {
            getTest().skip("⚠️ Test Skipped: " + result.getName());
            log.warn("⚠️ Test Skipped: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
            log.info("Browser closed");
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
        log.info("Extent report flushed");
    }

    /** Captures a screenshot to /Reports/screenshots/<name>.png */
    public static String captureScreenshot(WebDriver driver, String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "/Reports/screenshots/" + name + ".png";
        Path target = Paths.get(dest);
        Files.createDirectories(target.getParent());
        Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        return dest;
    }

    // Required for Listener
    public WebDriver getDriver() {
        return driver;
    }

    // Expose ExtentTest for Listener if needed
    public static ExtentTest getTest() {
        return test.get();
    }
}

