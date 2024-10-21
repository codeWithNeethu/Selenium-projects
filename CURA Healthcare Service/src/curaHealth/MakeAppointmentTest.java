package curaHealth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class MakeAppointmentTest {

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
	    public void testPositiveMakeAppointment() {
	        // Click on "Make Appointment" button
	        WebElement makeAppointmentBtn = driver.findElement(By.id("btn-make-appointment"));
	        makeAppointmentBtn.click();

	        // Login to the application
	        WebElement username = driver.findElement(By.id("txt-username"));
	        WebElement password = driver.findElement(By.id("txt-password"));
	        WebElement loginButton = driver.findElement(By.id("btn-login"));

	        username.sendKeys("John Doe");
	        password.sendKeys("ThisIsNotAPassword");
	        loginButton.click();

	        // Fill out the appointment form
	        WebElement facility = driver.findElement(By.id("combo_facility"));
	     // Create an instance of the Select class
	        Select facilitySelect = new Select(facility);

	        // Select the second option by index (index 1 for second option)
	        facilitySelect.selectByIndex(1);

	        WebElement applyForReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
	        applyForReadmission.click();

	        WebElement healthcareProgramMedicaid = driver.findElement(By.id("radio_program_medicaid"));
	        healthcareProgramMedicaid.click();

	        WebElement visitDate = driver.findElement(By.id("txt_visit_date"));
	        visitDate.sendKeys("20/10/2024");

	        WebElement comment = driver.findElement(By.id("txt_comment"));
	        comment.sendKeys("Looking forward to the appointment.");

	        // Submit the form
	        WebElement bookAppointmentBtn = driver.findElement(By.id("btn-book-appointment"));
	        bookAppointmentBtn.click();

	        // Validate that the confirmation page is displayed
	        WebElement confirmationMessage = driver.findElement(By.tagName("h2"));
	        Assert.assertEquals(confirmationMessage.getText(), "Appointment Confirmation");
	    }

	    
	    @Test
	    public void testNegativeMakeAppointmentWithoutDate() {
	        // Return to the appointment page
	        driver.get("https://katalon-demo-cura.herokuapp.com/#appointment");
	        
	     // Click on "Make Appointment" button
	        WebElement makeAppointmentBtn = driver.findElement(By.id("btn-make-appointment"));
	        makeAppointmentBtn.click();

	        // Login to the application
	        WebElement username = driver.findElement(By.id("txt-username"));
	        WebElement password = driver.findElement(By.id("txt-password"));
	        WebElement loginButton = driver.findElement(By.id("btn-login"));

	        username.sendKeys("John Doe");
	        password.sendKeys("ThisIsNotAPassword");
	        loginButton.click();


	        // Fill out the form but skip entering a date
	        WebElement facility = driver.findElement(By.id("combo_facility"));
		     // Create an instance of the Select class
	        Select facilitySelect = new Select(facility);

	        // Select the second option by index (index 1 for second option)
	        facilitySelect.selectByIndex(2);

	        WebElement applyForReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
	        applyForReadmission.click();

	        WebElement healthcareProgramMedicaid = driver.findElement(By.id("radio_program_medicaid"));
	        healthcareProgramMedicaid.click();

	        WebElement comment = driver.findElement(By.id("txt_comment"));
	        comment.sendKeys("I forgot to enter a date.");

	        // Try to submit the form
	        WebElement bookAppointmentBtn = driver.findElement(By.id("btn-book-appointment"));
	        bookAppointmentBtn.click();
            
	        WebElement datePicker = driver.findElement(By.xpath("//th[@class='datepicker-title']"));
	        datePicker.isDisplayed();
	        Assert.assertEquals(datePicker.isDisplayed(), false);
	    

	     
	    }

	    @After
	    public void teardown() {
	        // Close the browser after each test method
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
