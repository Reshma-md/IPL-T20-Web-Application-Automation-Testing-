package pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import java.time.Duration;

public class TeamsPage {
    WebDriver driver = DriverManager.getDriver();

    By cookieBtn = By.xpath("//button[contains(text(),'Accept') or contains(@id,'cookie') or contains(@class,'cookie')]");
    By teamCards = By.cssSelector("a[href*='/teams/']");

    public void handleCookieBanner() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cookieBtn));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
            System.out.println("Cookie banner closed.");
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("No cookie popup");
        }
    }

    public List<WebElement> getAllTeamCards() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='/teams/']")));
        List<WebElement> cards = driver.findElements(teamCards);
        System.out.println("Total team cards found: " + cards.size());
        return cards;
    }

    public String getTeamName(WebElement card) {
        try {
            String[] nameSelectors = {
                "[class*='name']", "[class*='title']", "h3", "h4", "p", "span"
            };
            for (String sel : nameSelectors) {
                try {
                    String text = card.findElement(By.cssSelector(sel)).getText().trim();
                    if (!text.isEmpty()) return text;
                } catch (Exception e) {
                    // try next
                }
            }
            // fallback: alt text of image
            String alt = card.findElement(By.cssSelector("img")).getDomAttribute("alt");
            return (alt != null) ? alt.trim() : "UNKNOWN";
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    public boolean isLogoDisplayed(WebElement card) {
        try {
            return card.findElement(By.cssSelector("img")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWinningYearsOnHover(WebElement card) {
        try {
            // Scroll card into center view
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});", card);
            Thread.sleep(800);

            // Real Selenium Actions hover
            Actions actions = new Actions(driver);
            actions.moveToElement(card).perform();
            Thread.sleep(2000);

            // Try to find winning years text after hover
            String[] yearSelectors = {
                "[class*='winner']", "[class*='title-year']", "[class*='won']",
                "[class*='year']", "[class*='champion']", "[class*='trophy']"
            };

            for (String sel : yearSelectors) {
                try {
                    List<WebElement> elements = card.findElements(By.cssSelector(sel));
                    for (WebElement el : elements) {
                        String text = el.getText().trim();
                        if (!text.isEmpty()) {
                            System.out.println("Found winning years with selector " + sel + ": " + text);
                            return text;
                        }
                    }
                } catch (Exception e) {
                    // try next
                }
            }

            return "NO_TITLES";
        } catch (Exception e) {
            System.out.println("Hover error: " + e.getMessage());
            return "NO_TITLES";
        }
    }
}