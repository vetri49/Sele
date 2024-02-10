package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver driver;
	LoginPage page;
	
	String siteUrl="http://demowebshop.tricentis.com/";
	
	String driverpath="drivers/windows/chromedriver.exe";
	
	@BeforeTest
	public  void setup() {

		System.setProperty("webdriver.chrome.driver", driverpath);
		 try {
	            driver = new ChromeDriver();
	            driver.get(siteUrl);
	            page = new LoginPage(driver);
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	        }
	}
	@AfterTest
	public void cleanup() {
		driver.quit();
	}
	@Test(priority=1)
	public void navigate_to_loginPage() {
		page=new LoginPage(driver);
		page.verifyTitle();
		
		
	}
	@Test(priority=2)
	public void enterCredentials() {
		page.enterEmail("bucec@gmail.com");
		page.enterPasswrod("owjwj123");
		page.verifyResult();
	}
	


}
