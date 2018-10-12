package com.qa.pages;

import com.qa.commons.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Hotels extends BaseSetup {

    private HotelsBookingObjects hotelsBookingObjects;

    public Hotels() {
        hotelsBookingObjects = new HotelsBookingObjects();
        PageFactory.initElements(driver, hotelsBookingObjects);
    }


    public void bookHotel(List<String> location) {


        //Navigating to hotels page
        hotelsBookingObjects.hotelLink.click();

        waitFor(10000);

        String[] loc = location.get(0).split(",");

        //Sending location
        hotelsBookingObjects.where.click();

        hotelsBookingObjects.where.sendKeys(loc[0]);
        waitFor(3000);
        List<WebElement> whereLocation= driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        waitFor(5000);
        whereLocation.get(1).click();
        waitFor(1000);


        //Sending check in date
        hotelsBookingObjects.checkInDate.click();
        waitFor(1000);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[2]/a")).click();
        waitFor(1000);


        //Sending check out date
        waitFor(5000);

        hotelsBookingObjects.checkOutDate.click();
        waitFor(3000);
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[5]/td[2]/a")).click();
        waitFor(2000);

        //Sending no. of travellers
        hotelsBookingObjects.travellersOnhome.click();
        Select people= new Select(hotelsBookingObjects.travellersOnhome);
        people.selectByIndex(1);

        //Search for hotels
        hotelsBookingObjects.searchHotelsButton.click();



    }

    private class HotelsBookingObjects {

        @FindBy(linkText = "Hotels")
        private WebElement hotelLink;

        @CacheLookup
        @FindBy(id = "Tags")
        private WebElement where;

        @CacheLookup
        @FindBy(id="CheckInDate")
        private WebElement checkInDate;

        @CacheLookup
        @FindBy(id = "CheckOutDate")
        private WebElement checkOutDate;


        @CacheLookup
        @FindBy(id = "travellersOnhome")
        private WebElement travellersOnhome;


        @CacheLookup
        @FindBy(id = "SearchHotelsButton")
        private WebElement searchHotelsButton;
    }
}
