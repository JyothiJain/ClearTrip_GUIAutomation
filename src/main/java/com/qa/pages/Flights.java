/*Author Jyothi Jain*/


package com.qa.pages;

import com.qa.commons.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Flights extends BaseSetup {

    private FlightBookingObjects fligtBookingObjects;

    public Flights() {
        fligtBookingObjects = new FlightBookingObjects();
        PageFactory.initElements(driver, fligtBookingObjects);
    }


    public boolean forOneWayJourney(List<String> locations) {

        //To select roundtrip option
        fligtBookingObjects.oneWay.click();

        String[] location = locations.get(0).split(",");

        System.out.println(location[0]);


        waitFor(10000);
        //Selecting from location
        fligtBookingObjects.fromLocation.click();
        fligtBookingObjects.fromLocation.sendKeys(location[0]);
        waitFor(5000);
        List<WebElement> fromLocation = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        waitFor(2000);
        fromLocation.get(0).click();

        //Selecting to location
        fligtBookingObjects.toLocation.click();
        fligtBookingObjects.toLocation.sendKeys(location[1]);
        waitFor(5000);
        List<WebElement> toLocation = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        toLocation.get(0).click();

        //Selecting depart on date
        fligtBookingObjects.departOn.click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[1]/td[6]/a")).click();


        //Submit for search
        fligtBookingObjects.searchFlightBtn.click();
        waitFor(5000);
        return true;
    }


    public boolean forRoundTrip(List<String> locations) {

        //To select roundtrip option
        fligtBookingObjects.roundTrip.click();

        String[] location = locations.get(0).split(",");

        waitFor(10000);

        //Selecting from location
        fligtBookingObjects.fromLocation.click();
        fligtBookingObjects.fromLocation.sendKeys(location[0]);
        waitFor(5000);
        List<WebElement> fromlocation = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        fromlocation.get(0).click();

        //Selecting to location
        fligtBookingObjects.toLocation.click();
        fligtBookingObjects.toLocation.sendKeys(location[1]);
        waitFor(5000);
        List<WebElement> tolocation = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        tolocation.get(0).click();

        //Selecting  depart date
        driver.findElement(By.id("DepartDate")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[9]/a")).click();

        //Selecting return date
        driver.findElement(By.id("ReturnDate")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[9]/a")).click();
        waitFor(5000);

        //Submit for search
        fligtBookingObjects.searchFlightBtn.click();
        waitFor(5000);
        return true;
    }

    public boolean forMultiCityTrip(List<String> locations) {


        waitFor(10000);

        //To select multitrip option
        fligtBookingObjects.multiCity.click();

        //Array for locations
        String[] locationOne = locations.get(0).split(",");
        String[] locationTwo = locations.get(1).split(",");

        //Selecting first from location
        fligtBookingObjects.fromLocationOne.click();
        fligtBookingObjects.fromLocationOne.sendKeys(locationOne[0]);
        waitFor(5000);
        List<WebElement> fromLocOptionOne = driver.findElement(By.id("ui-id-4")).findElements(By.tagName("li"));
        waitFor(7000);
        fromLocOptionOne.get(0).click();

        //Selecting first to location
        fligtBookingObjects.toLocationOne.click();
        fligtBookingObjects.toLocationOne.sendKeys(locationOne[1]);
        waitFor(5000);
        List<WebElement> toLocOptionOne = driver.findElement(By.id("ui-id-5")).findElements(By.tagName("li"));
        waitFor(5000);
        toLocOptionOne.get(0).click();

        //Selecting first depart date
        fligtBookingObjects.departOnOne.click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[7]/a")).click();

        //Selecting second from location
        fligtBookingObjects.fromLocationTwo.click();
        fligtBookingObjects.fromLocationTwo.sendKeys(locationTwo[0]);
        waitFor(5000);
        List<WebElement> fromLocOptionTwo = driver.findElement(By.id("ui-id-6")).findElements(By.tagName("li"));
        waitFor(5000);
        fromLocOptionTwo.get(0).click();

        //Selecting second to location
        fligtBookingObjects.toLocationTwo.click();
        fligtBookingObjects.toLocationTwo.sendKeys(locationTwo[1]);
        waitFor(5000);
        List<WebElement> toLocOptionTwo = driver.findElement(By.id("ui-id-7")).findElements(By.tagName("li"));
        waitFor(5000);
        toLocOptionTwo.get(0).click();

        //Selecting second depart date
        fligtBookingObjects.departOntwo.click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[2]/td[7]/a")).click();

        //Selecting the no. of adults
        Select adults = new Select(driver.findElement(By.id("Adults")));
        adults.selectByIndex(1);

        //Selecting the no. of childrens
        Select childern = new Select(fligtBookingObjects.children);
        childern.selectByIndex(2);

        //Selecting the no. of infants
        Select infants = new Select(fligtBookingObjects.infants);
        infants.selectByIndex(2);

        //Selecting the travel class
        Select classOfTravel = new Select(fligtBookingObjects.classOfTravel);
        classOfTravel.selectByIndex(2);

        //Submit for search
        fligtBookingObjects.searchFlightBtn.click();
        waitFor(5000);
        return true;

    }


    private class FlightBookingObjects {
        @CacheLookup
        @FindBy(id = "OneWay")
        private WebElement oneWay;


        @CacheLookup
        @FindBy(id = "RoundTrip")
        private WebElement roundTrip;


        @CacheLookup
        @FindBy(id = "FromTag")
        private WebElement fromLocation;

        @CacheLookup
        @FindBy(id = "ToTag")
        private WebElement toLocation;

        @CacheLookup
        @FindBy(id = "DepartDate")
        private WebElement departOn;


        @CacheLookup
        @FindBy(id = "ReturnDate")
        private WebElement returnDate;


        @CacheLookup
        @FindBy(id = "MultiCity")
        private WebElement multiCity;


        @CacheLookup
        @FindBy(id = "FromTag1")
        private WebElement fromLocationOne;

        @CacheLookup
        @FindBy(id = "ToTag1")
        private WebElement toLocationOne;

        @CacheLookup
        @FindBy(id = "DepartDate1")
        private WebElement departOnOne;


        @CacheLookup
        @FindBy(id = "FromTag2")
        private WebElement fromLocationTwo;

        @CacheLookup
        @FindBy(id = "ToTag2")
        private WebElement toLocationTwo;

        @CacheLookup
        @FindBy(id = "DepartDate2")
        private WebElement departOntwo;

        @CacheLookup
        @FindBy(id = "Adults")
        private WebElement adults;

        @CacheLookup
        @FindBy(id = "Childrens")
        private WebElement children;

        @CacheLookup
        @FindBy(id = "Infants")
        private WebElement infants;


        @CacheLookup
        @FindBy(id = "Class")
        private WebElement classOfTravel;


        @CacheLookup
        @FindBy(id = "SearchBtn")
        private WebElement searchFlightBtn;


    }

}
