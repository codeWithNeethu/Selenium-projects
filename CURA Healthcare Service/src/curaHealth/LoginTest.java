package curaHealth;

// Import necessary Selenium, WebDriver, and JUnit classes
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    // Declare WebDriver variable
    private WebDriver driver;

    // This method will run before each test
    @Before
    public void setUp() {
        // Set up WebDriver path (update path to your chromedriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\Chrome Driver New\\chromedriver-win64\\chromedriver.exe");
        
        // Initialize WebDriver (Chrome)
        driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Navigate to the CURA Healthcare Service demo site
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    // Test case for successful login
    @Test
    public void testLoginFunctionality() {
        // Click on the "Make Appointment" button to navigate to the login page
        WebElement makeAppointmentButton = driver.findElement(By.id("btn-make-appointment"));
        makeAppointmentButton.click();
        
        // Enter valid username and password
        WebElement usernameField = driver.findElement(By.id("txt-username"));
        usernameField.sendKeys("John Doe");  // Example username
        
        WebElement passwordField = driver.findElement(By.id("txt-password"));
        passwordField.sendKeys("ThisIsNotAPassword");  // Example password
        
        // Click the "Login" button
        WebElement loginButton = driver.findElement(By.id("btn-login"));
        loginButton.click();
        
        // Pause the execution for a few seconds to allow the next page to load
        try {
            Thread.sleep(3000);  // Sleep for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify that the appointment header is displayed after successful login
        WebElement appointmentHeader = driver.findElement(By.xpath("//h2[text()='Make Appointment']"));
        assertTrue("Login Test Failed: Appointment page not displayed.", appointmentHeader.isDisplayed());
    }

    // This method will run after each test
    @After
    public void tearDown() {
        // Close the browser and end the session
        if (driver != null) {
            driver.quit();
        }
    }
}
