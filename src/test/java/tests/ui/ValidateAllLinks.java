package tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ValidateAllLinks
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");

        //Locate and Capture all the links available in the Page
        List<WebElement> links = driver.findElements(By.tagName("a"));

        //Print the Total Number of Links Captured
        System.out.println("Total Links Captured : " + links.size());

        //Iterate through each link and Extract the URLs for Validation
        for (WebElement link : links)
        {
            String url = link.getAttribute("href");

            //Check for Empty URLs and Non HTTP URLs and Skip them
            if (url == null || url.isBlank() || !(url.startsWith("http://") || url.startsWith("https://")))
            {
                continue;
            }
            try
            {
                URL linkURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection)linkURL.openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setInstanceFollowRedirects(true);
                int response = connection.getResponseCode();

                //Retry with GET if HEAD is Unsupported
                if (response == 405 || response == 501)
                {
                    connection.disconnect();
                    connection = (HttpURLConnection) linkURL.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    response = connection.getResponseCode();
                }

                if (response >= 200 && response < 400)
                {
                    System.out.println("Valid Link : " + url + " | Status : " + response);
                }
                else
                {
                    System.out.println("Broken Link : " + url + " | Status : " + response);
                }
            }
            catch (Exception e)
            {
                System.out.println (" Unable to Validate : " + url + " | Error : " + e.getMessage());
            }
        }
        driver.quit();
    }
}