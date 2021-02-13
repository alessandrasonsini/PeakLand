package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;  

public class TestAssistedResearchSelenium {

	// Before to run test, turn on Tomcat server
	
	@Test
	public void testAssistedResearchByLevel() {
		boolean levelAsExpected = true;
        
		// Instantiate a SafariDriver class   
		WebDriver driver = new SafariDriver();
		
		// Maximize browser window size
		driver.manage().window().maximize();

        // Launch localhost  
		driver.navigate().to("http://localhost:8080/PeakLand_/home.jsp");  
            
        // Click on the search button 
		driver.findElement(By.name("VIEW_INFO")).click(); 
		
		// Wait until "E" radio button is available
		WebDriverWait waitResearch = new WebDriverWait(driver, 60);
		waitResearch.until(ExpectedConditions.presenceOfElementLocated(By.id("assistedResearch")));
				
		// AssistedResearch button click 
		driver.findElement(By.id("assistedResearch")).click();
		
		// Wait until "E" radio button is available
		WebDriverWait waitE = new WebDriverWait(driver, 60);
		waitE.until(ExpectedConditions.presenceOfElementLocated(By.id("E")));
		
		// Click on radio button Difficulty Level "E"
		driver.findElement(By.id("E")).click();
		
		// Click on search button
		driver.findElement(By.name("assResearch")).click();
		
		// Wait until container in which results are shown is available
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("difficultyLevel")));
		
		// Get all div of results with id = "difficultyLevel"
		List<WebElement> resultList = driver.findElements(By.id("difficultyLevel"));
		
		// Check that all the results have "E" (filter search) as field "difficultyLevel"
		// If not, levelAsExpected is set to false 
		for (WebElement elem : resultList) {
			if (!elem.getText().equals("E")) {
				levelAsExpected = false;
				break;
			}
		}
		driver.close(); 
		
		// Check test result
		assertTrue(levelAsExpected); 
	}
}