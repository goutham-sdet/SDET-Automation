package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Page Object class that contains Locators and its actions
public class LoginPage
{
    WebDriver driver;

    //Locators
    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");
    By homePageTitle = By.className("title");

    //Constructor
    public LoginPage (WebDriver driver)
    {
        this.driver = driver;
    }

    //Enter Username
    public void enterUsername(String user)
    {
        driver.findElement(username).sendKeys(user);
    }

    //Enter Password
    public void enterPassword(String pass)
    {
        driver.findElement(password).sendKeys(pass);
    }

    //Click Login
    public void clickLogin()
    {
        driver.findElement(loginButton).click();
    }

    //Reusable Login Method
    public void login(String user , String pass)
    {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    //Get Home page Title
    public String getHomePageTitle()
    {
        return driver.findElement(homePageTitle).getText();
    }
}