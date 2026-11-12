package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ClickAllCheckBoxes
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        //Locate All CheckBoxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        System.out.println("Total Checkboxes : " + checkboxes.size());

        //iterate through All Checkboxes
        for( WebElement cbox : checkboxes)
        {
            //Check Whether checkbox is already selected
            if (!cbox.isSelected())
            {
                cbox.click();
            }
        }

        //Validate All CheckBoxes Selection
        for (WebElement cbox : checkboxes)
        {
            if(cbox.isSelected())
            {
                System.out.println("Checkbox is Selected");
            }
            else
            {
                System.out.println("Checkbox is not Selected");
            }
        }

        driver.quit();
    }
}