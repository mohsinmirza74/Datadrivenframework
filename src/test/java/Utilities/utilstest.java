package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.*;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class utilstest {

    public static ExtentReports extent;
    public static ExtentTest test;
    public static Logger log = LogManager.getLogger(utilstest.class);

    /**
     * Initializes Extent Report once per suite.
     */
    public static void setupReport() {
        String reportPath = System.getProperty("user.dir") + "/Reports/TLSAutomationReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setDocumentTitle("TLS Customer Booking Report");
        reporter.config().setReportName("TLS Automation Execution");
        reporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Mohsin Latif");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        log.info("📄 Extent report initialized at: " + reportPath);
    }

    /**
     * Capture screenshot and save to /Screenshots folder with timestamp
     * Returns absolute normalized path (forward slashes) for report compatibility
     */
    public static String captureScreenshot(WebDriver driver, String name) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + name + "_" + timeStamp + ".png";

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destPath = Paths.get(screenshotPath);
            Files.createDirectories(destPath.getParent()); // Auto-create folder
            Files.copy(srcFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("📸 Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            log.error("❌ Failed to capture screenshot: " + e.getMessage());
        }

        // Normalize path for ExtentReport (use forward slashes)
        return screenshotPath.replace("\\", "/");
    }
}
