package tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ExtractTableData
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/tables");

        //Locate the Table
        WebElement table = driver.findElement(By.id("table1"));

        //Locate all rows and print the total number of Rows
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
        System.out.println("Totals Rows : " + rows.size());

        //Iterate through each row and locate all cells present in it
        for (WebElement row : rows)
        {
            List<WebElement> cells = row.findElements(By.xpath("./th | ./td"));

            //Iterate through each cell and print the values present in them
            for (WebElement cell : cells)
            {
                System.out.print(cell.getText() + " | ");
            }
            System.out.println();
        }
        driver.quit();
    }
}
