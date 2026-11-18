package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FluentWaitExample
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        //Create Fluent Wait
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        //Wait and Click the Element to enable Dyanamic Loading
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Start']"))).click();

        //Wait Until the Element/message becomes Visible
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        //Validate the Result
        System.out.println(message.getText());

        driver.quit();
    }
}