package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest
{
    @Test
    public void verifyLogin()
    {
        LoginPage loginPage = new LoginPage(driver);

        //Perform Login
        loginPage.login("standard_user" , "secret_sauce");

        //Validate successful Login
        String actualTitle = loginPage.getHomePageTitle();

        Assert.assertEquals(actualTitle , "Products", "Login failed!" );
    }
}
