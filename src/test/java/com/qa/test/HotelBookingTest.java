package com.qa.test;
import com.qa.commons.DataProvider;
import com.qa.utils.Screenshots;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.pages.Hotels;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class HotelBookingTest extends Hotels {


    @BeforeMethod
    public void initialSetup() {
        setup();
    }

    @Test(priority=1)
    public void testHotelBooking() throws IOException {
        DataProvider dataProvider = new DataProvider();
        List<String> location = dataProvider.readData("hotelBooking.csv");
        Hotels hotels = new Hotels();
        hotels.bookHotel(location);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.id("modifySearchLink")));
        //close the browser

        Screenshots.getScreenshots();
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

}
