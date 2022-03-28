package PageObject.RabotaBy.SearchResultPage;

import PageObject.BasePage;
import PageObject.RabotaBy.Homepage.DashboardElement;
import org.openqa.selenium.By;
import org.testng.Assert;

public class SearchRoot extends BasePage {
    private By reactRoot = By.id("HH-React-Root");
    private By searchResultCount = By.tagName("h1");

    public SearchRoot checkSearchRoot(){
        Assert.assertTrue(isElementExists(reactRoot));
        return this;
    }

    public SearchRoot checkSearchResult(String searchText, Integer searchCount){
        Assert.assertEquals(getText(searchResultCount),searchCount+" вакансия «"+searchText+"»");
        return this;
    }
}
