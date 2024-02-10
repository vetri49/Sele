package utilities;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyListener.class)
public class ItestListenersDemo {

    WebDriver driver;
    String siteUrl = "http://demowebshop.tricentis.com/";
    String driverPath = "drivers/windows/chromedriver.exe";

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        MyListener.setDriver(driver);
        driver.get(siteUrl);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void cleanup() {
         driver.quit();
    }

    @Test
    public void testOnListeners() {
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
        driver.findElement(By.id("Email")).sendKeys("crnvij@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("ci3jcijd111");
        driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click();

        assertEquals("Demo Web Shop", driver.getTitle());
    }
}
