package curaHealth;

import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload {

	
		
		WebDriver driver;

	    @Before
	    public void setup() {
	        // Set up the WebDriver and launch the browser
	        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\Chrome Driver New\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://the-internet.herokuapp.com/");
	        
	        driver.findElement(By.xpath("//a[text()='File Upload']")).click();
	    }
	    
	    @Test
	    public void test() {
	    	WebElement uploadfile = driver.findElement(By.id("file-upload"));
	        uploadfile.sendKeys("C:\\Resumes\\Technical Support_Neethu Johnson_CV.pdf");

	        // Click the 'Upload' button to submit the file
	        driver.findElement(By.id("file-submit")).click();
	        
	     // Verify if the upload was successful
	        WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));
	        Assert.assertTrue("File upload failed!", uploadedFileName.getText().contains("Technical Support_Neethu Johnson_CV.pdf"));

	        // Print confirmation to console
	        System.out.println("File uploaded successfully: " + uploadedFileName.getText());
	    
	    }
	    
	    @After
	    public void teardown() {
	    	driver.quit();
	    	
	    }
}

