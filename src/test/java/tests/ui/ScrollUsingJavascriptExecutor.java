package tests.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class ScrollUsingJavascriptExecutor
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");

        //Locate an Element which is beyond the current view
        WebElement hiddenElement = driver.findElement(By.linkText("Infinite Scroll"));

        //Initialize JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Scroll to View the Hidden Element
        js.executeScript("arguments[0].scrollIntoView(true);" , hiddenElement);

        //Validate the Visibility of the Element
        if(hiddenElement.isDisplayed())
        {
            System.out.println("Element is Visible");
        }

        //Scroll Back to Top of the Page
        js.executeScript("window.scrollTo(0 , 0);");
        //Locate an Element at the current View to validate scroll
        WebElement topElement = driver.findElement(By.linkText("Accessibility Issues"));

        //Validate an element visibility in that top area
        if (topElement.isDisplayed())
        {
            System.out.println("Top Element is visible");
        }

        //Scroll to a desired position
        js.executeScript("window.scrollBy(0 , 500);");

        //Locate an Element at the current View to validate scroll
        WebElement middleElement = driver.findElement(By.linkText("Autocomplete"));

        //Validate an element visibility in that scrolled area
        if (middleElement.isDisplayed())
        {
            System.out.println("Middle Element is Visible");
        }

        //Scroll to the Bottom of the Page
        js.executeScript("window.scrollTo(0 , document.body.scrollHeight);");

        //Locate and Element at the Bottom of the page to validate Scrolling
        WebElement bottomElement = driver.findElement(By.linkText("Disappearing Elements"));

        //Validate the visibility of the bottom element
        if (bottomElement.isDisplayed())
        {
            System.out.println("Bottom Element is Visible");
        }

        //Click the Bottom Element
        bottomElement.click();
        System.out.println("Bottom Element is clicked and Navigated to Disappearing Elements Page");

        driver.quit();
    }
}