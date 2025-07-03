package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtenttestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(ExtenttestListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("🚀 Test Suite Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        utilstest.test = utilstest.extent.createTest(testName);
        log.info("🟡 Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        utilstest.test.log(Status.PASS, "✅ Test Passed");
        log.info("✅ Test Passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        utilstest.test.log(Status.FAIL, "❌ Test Failed: " + throwable);
        log.error("❌ Test Failed: " + testName, throwable);

        try {
            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).getDriver();

            String screenshotPath = utilstest.captureScreenshot(driver, testName);
            utilstest.test.addScreenCaptureFromPath(screenshotPath);
            log.info("📸 Screenshot captured and attached to report: " + screenshotPath);

        } catch (Exception e) {
            log.error("❌ Failed to capture or attach screenshot: " + e.getMessage(), e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        utilstest.test.log(Status.SKIP, "⚠️ Test Skipped: " + throwable);
        log.warn("⚠️ Test Skipped: " + testName, throwable);
    }

    @Override
    public void onFinish(ITestContext context) {
        utilstest.extent.flush();
        log.info("📋 Test Suite Finished: " + context.getName());
    }
}
