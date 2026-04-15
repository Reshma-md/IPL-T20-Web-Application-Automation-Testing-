package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) {

	    String url = ConfigReader.getProperty("url");

	    DriverManager.initDriver(browser);
	    DriverManager.getDriver().get(url);
	    DriverManager.getDriver().manage().window().maximize();

	    handleCookies();   // 🔥 ADD THIS
	}
	public void handleCookies() {

	    try {
	        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

	        WebElement cookieBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[contains(text(),'Accept')]")));

	        cookieBtn.click();

	        System.out.println("Cookies accepted");

	    } catch (Exception e) {
	        System.out.println("No cookie popup");
	    }
	}
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}