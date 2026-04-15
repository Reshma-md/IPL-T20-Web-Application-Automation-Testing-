package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import base.BaseTest;
import pages.TeamsPage;
import utils.DriverManager;

public class TeamsTest extends BaseTest {

    @DataProvider(name = "teamWinningYears")
    public Object[][] teamWinningYearsData() {
        return new Object[][] {
            { "CHENNAI SUPER KINGS",         "2010, 2011, 2018, 2021, 2023" },
            { "MUMBAI INDIANS",              "2013, 2015, 2017, 2019, 2020" },
            { "KOLKATA KNIGHT RIDERS",       "2012, 2014, 2024"             },
            { "ROYAL CHALLENGERS BENGALURU", "2025"                         },
            { "GUJARAT TITANS",              "2022"                         },
            { "SUNRISERS HYDERABAD",         "2016"                         },
            { "RAJASTHAN ROYALS",            "2008"                         },
            { "DELHI CAPITALS",              ""                             },
            { "PUNJAB KINGS",               ""                             },
            { "LUCKNOW SUPER GIANTS",        ""                             }
        };
    }

    @Test
    public void verifyTeamDetailsAndWinningYears() {
        DriverManager.getDriver().get("https://www.iplt20.com/teams");

        TeamsPage teamsPage = new TeamsPage();

        // Step 1: Handle cookie banner
        teamsPage.handleCookieBanner();

        // Step 2: Load all team cards
        List<WebElement> teamCards = teamsPage.getAllTeamCards();

        // At least 5 cards should load
        Assert.assertTrue(teamCards.size() >= 5,
            "Expected at least 5 team cards, but found: " + teamCards.size());

        // Step 3: Build expected winners map from DataProvider
        Map<String, String> winnersMap = new HashMap<>();
        for (Object[] row : teamWinningYearsData()) {
            winnersMap.put((String) row[0], (String) row[1]);
        }

        // Step 4: Loop through cards - only process valid 10 teams
        int count = 0;
        for (WebElement card : teamCards) {
            if (count >= 10) break;

            String teamName = teamsPage.getTeamName(card).toUpperCase();

            // Skip cards with no name
            if (teamName.equals("UNKNOWN") || teamName.isEmpty()) {
                System.out.println("Skipping invalid card.");
                continue;
            }

            System.out.println("Processing team: " + teamName);

            // Assertion 1: Logo is displayed
            Assert.assertTrue(teamsPage.isLogoDisplayed(card),
                "Logo NOT visible for: " + teamName);
            System.out.println(teamName + " - Logo: PASS");

            // Assertion 2: Hover and check winning years
            String actualYears = teamsPage.getWinningYearsOnHover(card);
            System.out.println(teamName + " - Hover years detected: " + actualYears);

            if (winnersMap.containsKey(teamName)) {
                String expected = winnersMap.get(teamName);

                if (expected.isEmpty()) {
                    // Team has no titles
                    Assert.assertEquals(actualYears, "NO_TITLES",
                        teamName + " should have NO titles.");
                    System.out.println(teamName + " - No titles: PASS");
                } else {
                    // Normalize separator | to , and check
                    String normalizedActual = actualYears.replace(" | ", ", ").replace("|", ",").trim();
                    Assert.assertTrue(normalizedActual.contains(expected),
                        "Year mismatch for " + teamName +
                        ". Expected: [" + expected + "] Got: [" + normalizedActual + "]");
                    System.out.println(teamName + " - Winning years: PASS");
                }
            } else {
                System.out.println("SKIPPING unknown team (not in map): " + teamName);
            }

            count++;
        }

        System.out.println("Total teams verified: " + count);
    }
}