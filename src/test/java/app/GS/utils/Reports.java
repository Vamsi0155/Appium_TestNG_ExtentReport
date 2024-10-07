package app.GS.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reports extends BaseTest implements ITestListener {

    private final Logger logger = LogManager.getLogger("Reports.class");
    private ExtentReports extentReports;
    private ExtentTest test;
    private String timeStamp;


    @Override
    public void onStart(ITestContext context) {

        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        timeStamp = "Test_Report_" + timeStamp;

        ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//reports//" + timeStamp + "//index.html");
        reporter.config().setDocumentTitle("Automation Report");
        reporter.config().setReportName("General Store Test Report");
        reporter.config().setTimelineEnabled(true);

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project Name", "General Store");
        extentReports.setSystemInfo("Platform", "Android");
        extentReports.setSystemInfo("Automation Tester", "Vamsi Krishna");
        extentReports.setSystemInfo("OS Type", "Windows 10 Home");
        logger.info("{} Tests are started on {} platform!!", context.getCurrentXmlTest().getName(), context.getCurrentXmlTest().getParameter("Platform"));
    }

    @Override
    public void onTestStart(ITestResult result) {

        test = extentReports.createTest(result.getMethod().getDescription());
        logger.info("{} test is started", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS, "Test Passed");
        logger.info("{} test is passed successfully", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());
        try {
            AppiumDriver driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            File source = driver.getScreenshotAs(OutputType.FILE);
            String target = System.getProperty("user.dir")+"//reports//"+timeStamp+"//screenshots//"+result.getMethod().getMethodName()+".png";
            FileUtils.copyFile(source, new File(target));
            test.addScreenCaptureFromPath(target, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("{} test is failed with below error", result.getMethod().getMethodName());
        logger.info("failure reason is: {}", result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        logger.info("{} Tests are finished!!", context.getCurrentXmlTest().getName());
    }

}
