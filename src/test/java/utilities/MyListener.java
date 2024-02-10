package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class MyListener implements ITestListener {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    
	@Override
    public void onStart(ITestContext context) {
        // Initialize ExtentReports
        @SuppressWarnings("deprecation")
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        extentReports.setSystemInfo("Host Name", System.getProperty("user.name"));
       
            extentReports.setSystemInfo("Browser","Chrome");
       
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush and close ExtentReports after all tests are finished
        extentReports.flush();
    }

    

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success status in ExtentReports
        extentTest.log(Status.PASS, "Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            takeScreenshot("Loginfailure");
            System.out.println("Test failure");
        } else {
            System.out.println("Driver is null. Cannot take screenshot.");
        }
        
        extentTest = extentReports.createTest(result.getName()); // Create a new test in the report
        extentTest.log(Status.PASS, "Test Passed: " + result.getName());
    }

    public void takeScreenshot(String fileName) {
        if (driver == null) {
            System.out.println("Driver is null. Cannot take screenshot.");
            return;
        }
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String destinationPath = "screenshots/" + fileName + ".png";
        try {
            Files.createDirectories(Path.of("screenshots"));

            if (sourceFile.exists()) {
                Files.copy(sourceFile.toPath(), new File(destinationPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Screenshot captured: " + destinationPath);
            } else {
                System.out.println("Source file not found. Cannot copy screenshot.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
