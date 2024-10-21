package curaHealth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class LoginTestNegative {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\Chrome Driver New\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login"); // Replace with your login page URL
    }

    @Test
    public void testLoginWithIncorrectPassword() {
    	WebElement usernameField = driver.findElement(By.id("txt-username"));
        WebElement passwordField = driver.findElement(By.id("txt-password")); 
        WebElement loginButton = driver.findElement(By.id("btn-login")); 

        usernameField.sendKeys("John Doe");
        passwordField.sendKeys("Wrong");
        loginButton.click();

        // Check for error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()=\"Login failed! Please ensure the username and password are valid.\"]")); 
        assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testLoginWithIncorrectUsername() {
    	 WebElement usernameField = driver.findElement(By.id("txt-username")); // Update with your username field ID
         WebElement passwordField = driver.findElement(By.id("txt-password")); // Update with your password field ID
         WebElement loginButton = driver.findElement(By.id("btn-login"));

        usernameField.sendKeys("wrongUser");
        passwordField.sendKeys("ThisIsNotAPassword");
        loginButton.click();

        // Check for error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()=\"Login failed! Please ensure the username and password are valid.\"]")); 
        assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testLoginWithBothIncorrectUsernameAndPassword() {
    	 WebElement usernameField = driver.findElement(By.id("txt-username")); // Update with your username field ID
         WebElement passwordField = driver.findElement(By.id("txt-password")); // Update with your password field ID
         WebElement loginButton = driver.findElement(By.id("btn-login"));

        usernameField.sendKeys("wrongUser");
        passwordField.sendKeys("wrongPassword");
        loginButton.click();

        // Check for error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()=\"Login failed! Please ensure the username and password are valid.\"]")); 
        assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testLoginWithEmptyUsername() {
    	WebElement passwordField = driver.findElement(By.id("txt-password")); // Update with your password field ID
        WebElement loginButton = driver.findElement(By.id("btn-login"));

        passwordField.sendKeys("ThisIsNotAPassword");
        loginButton.click();

        // Check for error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()=\"Login failed! Please ensure the username and password are valid.\"]")); 
        assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void testLoginWithEmptyPassword() {
    	 WebElement usernameField = driver.findElement(By.id("txt-username")); 
    	 WebElement loginButton = driver.findElement(By.id("btn-login"));
    	 
        usernameField.sendKeys("John Doe");
        loginButton.click();

        // Check for error message
        WebElement errorMessage = driver.findElement(By.xpath("//p[text()=\"Login failed! Please ensure the username and password are valid.\"]")); 
        assertTrue(errorMessage.isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
