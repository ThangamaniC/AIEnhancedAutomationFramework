package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtilityListener implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extentReports;

    // Thread-safe ExtentTest
    private static ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        String reportPath =
                System.getProperty("user.dir")
                + "/target/extent-reports/ExtentReport.html";

        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config()
                .setReportName("AI Enhanced Automation Report");

        sparkReporter.config()
                .setDocumentTitle("Test Execution Report");

        extentReports = new ExtentReports();

        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo(
                "Environment", "QA");

        extentReports.setSystemInfo(
                "OS", System.getProperty("os.name"));

        extentReports.setSystemInfo(
                "Java Version", System.getProperty("java.version"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.set(
                extentReports.createTest(
                        result.getMethod().getMethodName()
                )
        );

        extentTest.get().pass("Test Passed");

        // Clear ThreadLocal after test is completed
        extentTest.remove();
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.set(
                extentReports.createTest(
                        result.getMethod().getMethodName()
                )
        );

        extentTest.get().fail("Test Failed");

        extentTest.get().fail(result.getThrowable());

        try {

            String screenshotPath =
                    ScreenshotUtil.getScreenshotPath();

            if (screenshotPath != null) {

                extentTest.get()
                        .addScreenCaptureFromPath(screenshotPath);
            }

            ScreenshotUtil.clearScreenshotPath();

        } catch (Exception e) {

            extentTest.get().warning(
                    "Unable to attach screenshot: "
                    + e.getMessage()
            );

        } finally {

            // Always clear ThreadLocal
            extentTest.remove();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.set(
                extentReports.createTest(
                        result.getMethod().getMethodName()
                )
        );

        extentTest.get().skip("Test Skipped");

        // Clear ThreadLocal after test is completed
        extentTest.remove();
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReports.flush();
    }
}