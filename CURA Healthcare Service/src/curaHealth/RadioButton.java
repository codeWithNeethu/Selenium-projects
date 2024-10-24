package curaHealth;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class RadioButton {
	
	WebDriver driver;

    @Before
    public void setUp() {
        // Set up Chrome driver (adjust the path to your system's ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\Chrome Driver New\\chromedriver-win64\\chromedriver.exe"); // Adjust path
        driver = new ChromeDriver();

        // Navigate to the page containing the radio buttons
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        
        WebElement makeAppointmentBtn = driver.findElement(By.id("btn-make-appointment"));
        makeAppointmentBtn.click();
        
        // Assume we need to log in before seeing the form (use your login credentials)
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");  // Replace with valid username
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");  // Replace with valid password
        driver.findElement(By.id("btn-login")).click();
        
       
    }

    @Test
    public void testRadioButtons() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // 10 seconds wait time
    	 
        // Locate radio buttons by their XPath or other identifiers
        WebElement medicareRadioButton = driver.findElement(By.xpath("//*[@id=\"radio_program_medicare\"]"));
        WebElement medicaidRadioButton = driver.findElement(By.id("radio_program_medicaid"));
        WebElement noneRadioButton = driver.findElement(By.id("radio_program_none"));

        // Step 1: Verify that the radio buttons are displayed
        Assert.assertTrue("Medicare radio button should be displayed", medicareRadioButton.isDisplayed());
        Assert.assertTrue("Medicaid radio button should be displayed", medicaidRadioButton.isDisplayed());
        Assert.assertTrue("None radio button should be displayed", noneRadioButton.isDisplayed());

        // Step 2: Select "Medicare" radio button and verify it is selected
        medicareRadioButton.click();
        Assert.assertTrue("Medicare radio button should be selected", medicareRadioButton.isSelected());
        Assert.assertFalse("Medicaid radio button should not be selected", medicaidRadioButton.isSelected());
        Assert.assertFalse("None radio button should not be selected", noneRadioButton.isSelected());

        // Step 3: Select "Medicaid" radio button and verify it is selected
        medicaidRadioButton.click();
        Assert.assertFalse("Medicare radio button should not be selected", medicareRadioButton.isSelected());
        Assert.assertTrue("Medicaid radio button should be selected", medicaidRadioButton.isSelected());
        Assert.assertFalse("None radio button should not be selected", noneRadioButton.isSelected());

        // Step 4: Select "None" radio button and verify it is selected
        noneRadioButton.click();
        Assert.assertFalse("Medicare radio button should not be selected", medicareRadioButton.isSelected());
        Assert.assertFalse("Medicaid radio button should not be selected", medicaidRadioButton.isSelected());
        Assert.assertTrue("None radio button should be selected", noneRadioButton.isSelected());
    }

    @After
    public void tearDown() {
        // Close the browser after test execution
        if (driver != null) {
            driver.quit();
        }
    }

}
