package com.qa.pages;

import com.qa.commons.BaseSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignIn extends BaseSetup {


    private SignInPageObjects signInPageObjects;

    public SignIn() {

        signInPageObjects = new SignInPageObjects();
        PageFactory.initElements(driver, signInPageObjects);
    }


    /*LoginMethod*/
    public boolean signIn(List<String> credentials) {
        String credential[] = credentials.get(0).split(",");
        signInPageObjects.emailId.sendKeys(credential[0]);
        signInPageObjects.password.sendKeys(credential[1]);
        signInPageObjects.signInButton.click();
        return true;
    }

    private class SignInPageObjects {
        @CacheLookup
        @FindBy(id = "email")
        private WebElement emailId;

        @CacheLookup
        @FindBy(id = "password")
        private WebElement password;

        @CacheLookup
        @FindBy(id = "signInButton")
        private WebElement signInButton;
    }


}

