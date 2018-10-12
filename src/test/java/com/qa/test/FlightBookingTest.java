package com.qa.test;

import org.apache.commons.io.FileUtils;

import com.qa.commons.DataProvider;
import com.qa.utils.Screenshots;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.qa.pages.Flights;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FlightBookingTest extends Flights {

    public ExtentReports extent;
    public ExtentTest extentTest;


    @BeforeTest
    public void setExtent() {

        extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ClearTrip.html", true);
        extent.addSystemInfo("Host Name", "Jyothi Dell");
        extent.addSystemInfo("User Name", "Jyothi Jain");
        extent.addSystemInfo("Environment", "QA");
    }


    @BeforeMethod
    public void initialSetup() {
        setup();
    }


    @Test(priority = 3)
    public void testMultiCityTrip() throws IOException {
        DataProvider dataProvider = new DataProvider();
        List<String> location = dataProvider.readData("flightBooking.csv");
        Flights flights = new Flights();
        flights.forMultiCityTrip(location);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.id("modifySearchLink")));

        //to generate a screenshot
        Screenshots.getScreenshots();

    }


    @Test(priority = 2)
    public void testRoundTrip() throws IOException {
        DataProvider dataProvider = new DataProvider();
        List<String> location = dataProvider.readData("flightBooking.csv");
        Flights flights = new Flights();
        flights.forRoundTrip(location);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.id("modifySearchLink")));


        //to generate a screenshot
        Screenshots.getScreenshots();
    }

    @Test(priority = 1)
    public void testOneWayTrip() throws IOException {
        DataProvider dataProvider = new DataProvider();
        List<String> location = dataProvider.readData("flightBooking.csv");
        Flights flights = new Flights();
        flights.forOneWayJourney(location);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.id("modifySearchLink")));


        //to generate a screenshot
        Screenshots.getScreenshots();
    }


    @AfterTest
    public void endReport() {

        extent.flush();


    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);
        //Store the screenshot in a location required
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + "_" + dateName
                + ".png";
        File finalDestination = new File(destination);

        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return destination;

    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        extentTest = extent.startTest("Test Name", "Description");
        String dateName = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); //to add name in extent report
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); //to add error/exception in extent report
            String screenshotPath = FlightBookingTest.getScreenshot(driver, result.getName());


            String path = "../FailedTestsScreenshots/" + screenshotPath.substring(60);
            System.out.println(path);
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(path)); //to add screenshot in extent report
            //extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

        }


        extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report


        //close the browser
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterSuite
    public void afterSuiteMethod() {
        extent.close();
    }
}


