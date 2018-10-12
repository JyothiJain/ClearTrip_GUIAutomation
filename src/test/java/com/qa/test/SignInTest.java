package com.qa.test;
import com.qa.commons.BaseSetup;
import com.qa.commons.DataProvider;
import com.qa.pages.Login;
import com.qa.pages.SignIn;
import com.qa.utils.Screenshots;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class SignInTest extends BaseSetup{

    Screenshots screenshots  = new Screenshots();;

    @BeforeMethod
    public void setup() {
        setDriverPath();
        intialize();
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
    }

    @Test (priority = 1)
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws IOException {
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        driver.switchTo().frame("modal_window");
        driver.findElement(By.id("signInButton")).click();
        String errors1 = driver.findElement(By.id("errors1")).getText();

        Assert.assertTrue(errors1.contains("There were errors in your submission"));

        Screenshots.getScreenshots();
        driver.quit();
    }

    @Test (priority = 2)
    public void successfulLogin() throws IOException {
        DataProvider dataProvider= new DataProvider();
        List<String> credentials = dataProvider.readData("login.csv");
        Login login =new Login();
        login.toSignIn();
        driver.switchTo().frame("modal_window");
        SignIn signIn = new SignIn();
        signIn.signIn(credentials);
        waitFor(10000);
        String profileName = driver.findElement(By.id("userAccountLink")).getAttribute("title");
        Assert.assertEquals("jyothi92.jain@gmail.com", profileName);
        Screenshots.getScreenshots();
        driver.quit();

    }



}
