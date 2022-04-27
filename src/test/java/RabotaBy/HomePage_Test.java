package RabotaBy;

import BaseObjects.BaseTest;
import PageObject.RabotaBy.ContentPage.HomePageContent.HomePage;
import PageObject.RabotaBy.ContentPage.SearchPageContent.SearchRoot;
import PageObject.RabotaBy.FooterPage.FooterElement;
import PageObject.RabotaBy.HeaderPage.HeaderElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePage_Test extends BaseTest {
@Parameters("search")
    @Test(priority = 1)
    public void checkMainPage_Test() {
        get(HomePage.class)
                .open()
                .getMainElement()
                .checkMainElement()
                .getDashboardElement()
                .checkDashboardContent();

        get(HeaderElement.class).checkHeaderElement();
        get(FooterElement.class).checkFooterElement();
        get(HomePage.class).getMainElement().enterSearch("Automation QA");
    }

   @Parameters("search")
    @Test(priority = 1)
    public void checkSearchPage_Test() {
        get(SearchRoot.class).checkSearchRoot().checkSearchResult("Automation QA", 1);
    }
}
