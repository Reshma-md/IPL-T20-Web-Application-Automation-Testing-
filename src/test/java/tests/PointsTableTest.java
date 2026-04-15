package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.PointsTablePage;
import utils.DriverManager;

public class PointsTableTest extends BaseTest {

    @Test
    public void verifyPointsTable() {
        DriverManager.getDriver().get("https://www.iplt20.com/points-table/2025");

        PointsTablePage pointsTablePage = new PointsTablePage();

        // Step 1: Handle cookie banner
        pointsTablePage.handleCookieBanner();

        // Step 2: Get Rank 1 team name
        String rankOneTeam = pointsTablePage.getRankOneTeamName();
        System.out.println("Rank 1 Team: " + rankOneTeam);

        Assert.assertNotEquals(rankOneTeam, "UNKNOWN",
            "Could not retrieve rank 1 team name");
        Assert.assertFalse(rankOneTeam.isEmpty(),
            "Rank 1 team name is empty");
        System.out.println("Team Name check: PASS -> " + rankOneTeam);

        // Step 3: Matches Played must be > 0
        int matchesPlayed = pointsTablePage.getMatchesPlayedForRankOne();
        System.out.println("Rank 1 Matches Played: " + matchesPlayed);

        Assert.assertTrue(matchesPlayed > 0,
            "Matches played should be > 0, but got: " + matchesPlayed);
        System.out.println("Matches Played check: PASS -> " + matchesPlayed);

        // Step 4: Points must be > 0
        int points = pointsTablePage.getPointsForRankOne();
        System.out.println("Rank 1 Points: " + points);

        Assert.assertTrue(points > 0,
            "Points should be > 0, but got: " + points);
        System.out.println("Points check: PASS -> " + points);

        System.out.println("Points Table Test PASSED for team: " + rankOneTeam);
    }
}