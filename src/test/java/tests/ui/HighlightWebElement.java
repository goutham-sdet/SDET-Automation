package tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HighlightWebElement
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        //Locate the WebElements
        WebElement userField = driver.findElement(By.id("user-name"));
        WebElement passField = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.id("login-button"));

        //Initialize JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Script for Modifying CSS properties of Elements
        js.executeScript("arguments[0].style.border='3px solid pink';" , userField);
        js.executeScript("arguments[0].style.backgroundColor = 'blue';" , passField);
        js.executeScript("arguments[0].style.border = '2px solid green';" , loginBtn);
        System.out.println ("Modification Done!!");

        //Script for Removing the modification done for CSS properties of Elements
        js.executeScript("arguments[0].style.border='';" , userField);
        js.executeScript("arguments[0].style.backgroundColor = '';" , passField);
        js.executeScript("arguments[0].style.border = '';" , loginBtn);
        System.out.println ("Modification Removed!!");

        driver.quit();
    }
}