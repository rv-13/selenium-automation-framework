package com.projectx.utils;

import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 5;

    public static String generateTimeStamp() {
        Date date = new Date();
        return date.toString().replace(" ", "_").replace(":", "_");
    }

    public static String generateEmailWithTimeStamp() {
        Date date = new Date();
        String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
        return "rv" + timeStamp + "@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName) throws IOException {
        File fileExcel = new File(System.getProperty("user.dir") + "/src/main/java/com/projectx/testdata/TestData_SeleniumFramework1.xlsx");
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fileInputStreamExcel = new FileInputStream(fileExcel);
            workbook = new XSSFWorkbook(fileInputStreamExcel);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();
        Object[][] data = new Object[rows][cols];
        XSSFRow row;
        XSSFCell cell = null;
        CellType cellType;
        for (int i = 0; i < rows; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < cols; j++) {
                cell = row.getCell(j);
                cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                        break;
                }
            }
        }

        return data;
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + Utilities.generateTimeStamp() + ".PNG";
        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destinationScreenshotPath;
    }
}
