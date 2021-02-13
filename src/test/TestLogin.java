package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;  
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;  

public class TestLogin {

	// Before to run test, turn on Tomcat server
	
	@Test
	public void testLoginName() {
		
		// Instantiate a SafariDriver class
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Maximize browser window size
		driver.manage().window().maximize();

        // Launch localhost  
		driver.navigate().to("http://localhost:8080/PeakLand_/home.jsp");
		
		// Click on the profile button 
		driver.findElement(By.name("PROFILE")).click(); 
		
		// Wait until textbox in which user name is shown is available
		WebDriverWait waitLoginButton = new WebDriverWait(driver, 60);
		waitLoginButton.until(ExpectedConditions.presenceOfElementLocated(By.id("login")));
		
		// Insert username and password and click to login
		driver.findElement(By.id("username")).sendKeys("test");
		driver.findElement(By.id("password")).sendKeys("test");
		driver.findElement(By.id("login")).click();
		
		// Wait until textbox in which user name is shown is available
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
		
		// Get element name
		WebElement resultName = driver.findElement(By.id("name"));
		
		String actualResult = resultName.getAttribute("value");
		String expectedResult = "NameTest";
		
		driver.close(); 
		
		assertEquals(expectedResult, actualResult);
				
	}
	
	
	
	
}