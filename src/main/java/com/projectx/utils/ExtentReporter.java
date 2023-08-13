package com.projectx.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReports = new ExtentReports();

        File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html");
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(extentReportFile);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName("Selenium Test Automation");
        extentSparkReporter.config().setDocumentTitle("Test Execution Report");
        extentSparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReports.attachReporter(extentSparkReporter);
        Properties configProperties = new Properties();
        try {
            File configFile = new File(System.getProperty("user.dir") + "/src/main/java/com/projectx/config/config.properties");
            FileInputStream fileInputStreamConfigProp = new FileInputStream(configFile);
            configProperties.load(fileInputStreamConfigProp);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Application URL", configProperties.getProperty("url"));
        extentReports.setSystemInfo("Browser Name", configProperties.getProperty("browser"));
        extentReports.setSystemInfo("TestEmail", configProperties.getProperty("validEmail"));
        extentReports.setSystemInfo("TestPassword", configProperties.getProperty("validPassword"));
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));

        return extentReports;
    }
}

