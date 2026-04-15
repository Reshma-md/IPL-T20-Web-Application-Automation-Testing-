package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());

        } 
        else if (browser.equalsIgnoreCase("edge")) {

            try {
                // Try WebDriverManager first
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());

            } catch (Exception e) {

                // Fallback to manual driver path
                String path = ConfigReader.getProperty("edgedriverpath");
                System.setProperty("webdriver.edge.driver", path);
                driver.set(new EdgeDriver());
            }
        } 
        else {
            throw new RuntimeException("Invalid browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }
}