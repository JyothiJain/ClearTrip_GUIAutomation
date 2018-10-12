package com.qa.utils;

import com.qa.commons.BaseSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshots extends BaseSetup {

    //take a screenshot
    public static void getScreenshots() throws IOException {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //Store the screenshot in a location required
        File destinationFile = new File("/home/jyothi/IdeaProjects/Automation/src/test/java/com/qa/screenshots/cleartrip" + System.currentTimeMillis() + ".png");

        //copy the screenshots frrom source to destination
        FileHandler.copy(sourceFile, destinationFile);

    }
}


