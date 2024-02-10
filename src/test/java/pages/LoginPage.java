package pages;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	By login=By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a");
	By Email = By.id("Email");
	By password = By.id("Password");

	public LoginPage(WebDriver driver) {
	this.driver = driver;
	}
	public void verifyTitle() {
		String getheader = driver.findElement(login).getText().trim();
		driver.findElement(login).click();
		assertEquals("Log in", getheader);
		}
	
		public void enterEmail(String email) {
		driver.findElement(Email).sendKeys(email);
		}
		
		public void enterPasswrod(String Passcode) {
		driver.findElement(password).sendKeys(Passcode);
		}
		public void verifyResult() {
			assertEquals("Demo Web Shop. Login",driver.getTitle());
		}

}
