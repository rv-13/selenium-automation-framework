package com.projectx.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.projectx.utils.ExtentReporter;
import com.projectx.utils.Utilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ProjectListeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    WebDriver driver;
    String testName;

    @Override
    public void onStart(ITestContext iTestContext) {
        extentReports = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        testName = iTestResult.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, testName + ": Started Executing");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.log(Status.PASS, testName + ": Test Successfully Executed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            driver = (WebDriver) iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        extentTest.addScreenCaptureFromPath(Utilities.captureScreenshot(driver, testName));
        extentTest.log(Status.INFO, iTestResult.getThrowable());
        extentTest.log(Status.FAIL, testName + ": Got Failed!");
        System.out.println("Screenshot Captured!!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentTest.log(Status.SKIP, testName + ": Got Skipped!");
        extentTest.log(Status.INFO, iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }


    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finished executing Project Tests!");
        extentReports.flush();
        String extentReportPath = System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html";
        File extentReportFile = new File(extentReportPath);
        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
