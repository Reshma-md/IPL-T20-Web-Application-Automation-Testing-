package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.NewsPage;

public class NewsTest extends BaseTest {

    @Test
    public void verifyNewsSearch() {

        NewsPage page = new NewsPage();

        page.openNews();
        page.searchNews("Auction 2026");

        int count = page.getResultsCount();
        System.out.println("Search Results: " + count);

        // ✅ Step 1: Results exist
        Assert.assertTrue(count > 0, "No results found");

        // 🔥 Step 2: Relevant content validation
        Assert.assertTrue(
                page.isRelevantResultPresent("Auction 2026"),
                "No relevant articles found for Auction 2026"
        );
    }
}