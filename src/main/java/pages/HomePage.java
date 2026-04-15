package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DriverManager;
import utils.WaitUtils;

public class HomePage {

    WebDriver driver = DriverManager.getDriver();

    By teamsTab =By.xpath("//ul[contains(@class,'site-menu main-menu js-clone-nav d-none d-lg-block textCenter')]//a[contains(@onclick,'click_menu(this)')][normalize-space()='TEAMS']");
    //By newsTab = By.linkText("News");

    public void clickTeams() {
    	WaitUtils.waitForElement(teamsTab).click();
    }
    By acceptCookies = By.xpath("//button[contains(text(),'Accept cookies')]");

    public void acceptCookiesIfPresent() {

        WebDriver driver = DriverManager.getDriver();

        try {
            // Small wait (safe, won’t affect other tests much)
            WaitUtils.smallWait(2);

            // Try normal button first (your original flow but improved)
            if (clickAccept()) {
                System.out.println("✅ Cookies accepted");
                return;
            }

            // Try iframe (only if needed)
            for (WebElement frame : driver.findElements(By.tagName("iframe"))) {
                driver.switchTo().frame(frame);

                if (clickAccept()) {
                    System.out.println("✅ Cookies accepted inside iframe");
                    driver.switchTo().defaultContent();
                    return;
                }

                driver.switchTo().defaultContent();
            }

            System.out.println("⚠ No cookie popup found");

        } catch (Exception e) {
            System.out.println("⚠ Cookie handling skipped");
        }
        
    }
    private boolean clickAccept() {
        try {
            WebElement btn = driver.findElement(
                    By.xpath("//button[contains(.,'Accept')]")
            );

            if (btn.isDisplayed()) {
                btn.click();
                return true;
            }

        } catch (Exception ignored) {}

        return false;
    }
}