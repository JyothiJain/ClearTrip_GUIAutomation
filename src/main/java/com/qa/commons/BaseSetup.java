package com.qa.commons;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class BaseSetup {

    public static WebDriver driver;

    public static void intialize() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

    }

    protected void setDriverPath() {


            System.setProperty("webdriver.chrome.driver", "/home/jyothi/IdeaProjects/Automation/chromedriver");

        }

    public void setup() {
        setDriverPath();
        intialize();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
    }





    protected void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }





}
