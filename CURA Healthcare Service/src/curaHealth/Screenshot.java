package curaHealth;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Screenshot {

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
        Select facilitySelect = new Select(facility);
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
    }

    @After
    public void takeScreenshotAndTearDown() {
        // Capture screenshot after making an appointment
        takeScreenshot("appointment_confirmation");

        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    // Updated method to take a screenshot using a string as filename
    public void takeScreenshot(String filename) {
        // Cast the driver to TakesScreenshot
        TakesScreenshot screenshot = ((TakesScreenshot) driver);

        // Capture the screenshot and store it as a file
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            // Define the destination file path
            Path destFile = Paths.get("C:\\Screenshots\\" + filename + ".png");

            // Create directories if they do not exist
            Files.createDirectories(destFile.getParent());

            // Save the screenshot
            Files.copy(srcFile.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot taken and saved to: " + destFile.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
