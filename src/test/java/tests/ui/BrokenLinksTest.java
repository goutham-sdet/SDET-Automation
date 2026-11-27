package tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;

public class BrokenLinksTest
{
    public static void main (String[] args)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total Links : " + links.size());

        int brokenCount = 0 , validCount = 0 , skipCount = 0;

        for (WebElement link : links)
        {
            String url = link.getAttribute("href");

            if(url == null || url.isBlank() || !(url.startsWith("http://") || url.startsWith("https://")))
            {
                skipCount++;
                continue;
            }
            try
            {
                HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setInstanceFollowRedirects(true);
                int responseCode = connection.getResponseCode();

                if (responseCode == 405 || responseCode == 501 || responseCode == 403)
                {
                    connection.disconnect();
                    connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setInstanceFollowRedirects(true);
                    responseCode = connection.getResponseCode();
                }

                if (responseCode >= 400)
                {
                    System.out.println ("Broken Link : " + url + " | Status : " + responseCode);
                    brokenCount++;
                }
                else
                {
                    System.out.println("Valid Link : " + url + " | Status : " + responseCode);
                    validCount++;
                }
                connection.disconnect();
            }
            catch (Exception e)
            {
                System.out.println("Failed : " + url + " Error : " + e.getMessage());
                brokenCount++;
            }
        }

        System.out.println("\n-----------Summary-----------");
        System.out.println("Valid Links : " + validCount);
        System.out.println("Broken Links : " + brokenCount);
        System.out.println("Skipped Links : " + skipCount);
        driver.quit();
    }
}
