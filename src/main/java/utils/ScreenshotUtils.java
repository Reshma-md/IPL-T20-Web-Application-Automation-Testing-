package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils {

	public static String captureScreenshot(String testName) {

	    WebDriver driver = DriverManager.getDriver();

	    if (driver == null) {
	        System.out.println("Driver is null. Screenshot skipped.");
	        return null;
	    }

	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String filePath = "screenshots/" + testName + "_" + timestamp + ".png";

	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File src = ts.getScreenshotAs(OutputType.FILE);
	        File dest = new File(filePath);

	        FileHandler.copy(src, dest);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return filePath;
	}
}