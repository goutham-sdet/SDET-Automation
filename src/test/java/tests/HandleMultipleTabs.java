package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.Set;

public class HandleMultipleTabs
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get ("https://the-internet.herokuapp.com/windows");

        //Store and Print Parent Window ID
        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window ID : " + parentWindow);

        //Click the Link to Open New Window (Child window)
        driver.findElement(By.linkText("Click Here")).click();

        //Collect and Store all Windows ID's
        Set<String> allWindows = driver.getWindowHandles();

        //Print Total Windows Count
        System.out.println("Total Windows : " + allWindows.size());

        //Iterate and Switch To Child Window
        for (String window : allWindows)
        {
            if(!window.equals(parentWindow))
            {
                driver.switchTo().window(window);
                //Print Child window Title
                System.out.println("Child Window Title : " + driver.getTitle());

                //Validate Child Window
                String heading = driver.findElement(By.tagName("h3")).getText();
                System.out.println("Heading : " + heading);
            }
        }
        //Switch Back to Parent Window
        driver.switchTo().window(parentWindow);
        //Validate Parent Window
        System.out.println("Parent window Title : " + driver.getTitle());

        driver.quit();
    }
}