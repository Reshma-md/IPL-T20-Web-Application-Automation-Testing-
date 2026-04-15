package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import utils.WaitUtils;
import java.time.Duration;
import java.util.List;

public class PointsTablePage {

    WebDriver driver = DriverManager.getDriver();

    By cookieBtn    = By.xpath("//button[contains(text(),'Accept cookies')]");
    By tableRows    = By.xpath("//table//tr[td]");
    By rankOneCells = By.xpath("(//table//tr[td])[1]/td");

    public void handleCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            Thread.sleep(1000);
        } catch (Exception e) {
            // No cookie popup present
        }
    }

    public String getRankOneTeamName() {
        try {
            WaitUtils.waitForElement(tableRows);
            List<WebElement> cells = driver.findElements(rankOneCells);
            if (cells.size() > 2) {
                String name = cells.get(2).getText().trim();
                if (!name.isEmpty()) return name;
            }
        } catch (Exception e) {
            // Unable to retrieve rank 1 team name
        }
        return "UNKNOWN";
    }

    public int getMatchesPlayedForRankOne() {
        try {
            WaitUtils.waitForElement(tableRows);
            List<WebElement> cells = driver.findElements(rankOneCells);
            if (cells.size() > 3) {
                return Integer.parseInt(cells.get(3).getText().trim());
            }
        } catch (Exception e) {
            // Unable to retrieve matches played
        }
        return 0;
    }

    public int getPointsForRankOne() {
        try {
            WaitUtils.waitForElement(tableRows);
            List<WebElement> cells = driver.findElements(rankOneCells);
            if (cells.size() > 10) {
                return Integer.parseInt(cells.get(10).getText().trim());
            }
        } catch (Exception e) {
            // Unable to retrieve points
        }
        return 0;
    }
}