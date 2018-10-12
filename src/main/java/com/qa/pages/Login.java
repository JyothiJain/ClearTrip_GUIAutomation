package com.qa.pages;
import com.qa.commons.BaseSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
    /**
     * Created by jyothijain on 16/08/18.
     */
    public class Login extends BaseSetup{
        private LoginPageObjects loginPageObjects;

        public Login() {
            loginPageObjects = new LoginPageObjects();
            PageFactory.initElements(driver, loginPageObjects);
        }


        /*LoginMethod*/
        public boolean toSignIn() {
            loginPageObjects.yourTripsDropDown.click();
            loginPageObjects.signIn.click();
            return true;
        }

        private class LoginPageObjects {

            @CacheLookup
            @FindBy(id = "userAccountLink")
            private WebElement yourTripsDropDown;

            @CacheLookup
            @FindBy(id = "SignIn")
            private WebElement signIn;


        }


    }
