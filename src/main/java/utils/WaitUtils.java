package utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class WaitUtils {

    public static WebElement waitForElement(By locator) {

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(10)
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForVisibilityInsideElement(WebElement parent, By locator) {

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(10)
        );

        return wait.until(d -> {
            try {
                WebElement element = d.findElement(locator); // use driver here
                return element.isDisplayed() ? element : null;
            } catch (Exception e) {
                return null;
            }
        });
    }

    // 🔥 NEW: Wait until element is clickable
    public static WebElement waitForClickable(By locator) {

        WebDriverWait wait = new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(10)
        );

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    // 🔥 NEW: Generic sleep replacement (better than Thread.sleep)
    public static void smallWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}