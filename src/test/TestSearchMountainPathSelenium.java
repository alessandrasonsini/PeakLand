package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSearchMountainPathSelenium {

	// Before to run test, turn on Tomcat server

	@Test
	public void testSearchMountainPathSelenium() throws InterruptedException {
		boolean nameAsExpected = true;
        
		// Instantiate a ChromeDriver class   
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Maximize browser window size
		driver.manage().window().maximize();

        // Launch localhost  
		driver.navigate().to("http://localhost:8080/PeakLand_/index.jsp");  
            
        // Click on the view info button 
		driver.findElement(By.name("VIEW_INFO")).click(); 
		
		// Search for a specific path name
		driver.findElement(By.name("pathName")).sendKeys("Val");
		
		// Click search button
		driver.findElement(By.name("btnSearchPath")).click();
		
		// Get all results with id = "pathNameCardView"
		List<WebElement> resultList = driver.findElements(By.id("pathNameCardView"));
		
		// Check that all the results name start with "Val"
		// If not, nameAsExpected is set to false 
		
		for (WebElement elem : resultList) {
			if (!elem.getText().startsWith("Val")) {
				nameAsExpected = false;
				break;
			}
		}
		driver.close(); 
		
		// Check test result
		assertTrue(nameAsExpected); 
	}
}