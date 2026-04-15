package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import utils.DriverManager;
import utils.WaitUtils;

public class NewsPage {

    WebDriver driver = DriverManager.getDriver();

    By newsTab = By.xpath("//a[contains(text(),'News')]");
    By searchIcon = By.xpath("//button[contains(@class,'search') or contains(@class,'Search')]");
    By searchBox = By.xpath("//input[@type='search' or @placeholder]");
    By searchResults = By.xpath("//div[contains(@class,'news') or contains(@class,'card')]");

    public void openNews() {
        WaitUtils.waitForElement(newsTab).click();
    }

    public void searchNews(String text) {
        WaitUtils.waitForElement(searchIcon).click();
        WaitUtils.waitForElement(searchBox).sendKeys(text + "\n");
    }

    public int getResultsCount() {
        return driver.findElements(searchResults).size();
    }

    // 🔥 NEW METHOD: Validate relevance
    public boolean isRelevantResultPresent(String keyword) {
        List<WebElement> results = driver.findElements(searchResults);

        for (WebElement el : results) {
            String text = el.getText().toLowerCase();
            if (text.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}