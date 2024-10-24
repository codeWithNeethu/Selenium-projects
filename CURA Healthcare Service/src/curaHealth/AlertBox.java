package curaHealth;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AlertBox {

	
		WebDriver driver;

	    @Before
	    public void setup() {
	        // Set up the WebDriver and launch the browser
	        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\Chrome Driver New\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://katalon-demo-cura.herokuapp.com/");
	    }
	        
	        @Test
	        public void loginAndCheckAlert() {
	        WebElement makeAppointmentBtn = driver.findElement(By.id("btn-make-appointment"));
	        makeAppointmentBtn.click();

	        // Login to the application
	        WebElement username = driver.findElement(By.id("txt-username"));
	        WebElement password = driver.findElement(By.id("txt-password"));
	        WebElement loginButton = driver.findElement(By.id("btn-login"));

	        username.sendKeys("John Doe");
	        password.sendKeys("ThisIsNotAPassword");
	        loginButton.click();
	        
	       
	        // Wait for the alert to appear (if it does)
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Updated for Selenium 4
	        try {
	            // Wait until the alert is present
	            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

	            // Validate that the alert is displayed
	            Assert.assertNotNull(alert);

	            // Print alert text (optional)
	            System.out.println("Alert text: " + alert.getText());

	            // Accept the alert (click OK)
	            alert.accept();
	            
	            System.out.println("Alert was successfully handled.");
	            
	        } catch (Exception e) {
	            // If no alert is present, handle the exception
	            System.out.println("No alert was present after login.");
	        }

	    }
	    
	    @After
	    public void tearDown() {
	        // Close the browser after the test
	        if (driver != null) {
	            driver.quit();
	        }

}
	    }
