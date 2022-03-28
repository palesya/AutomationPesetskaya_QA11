package RabotaBy;

import BaseObjects.BaseTest;
import PageObject.RabotaBy.Homepage.DashboardElement;
import PageObject.RabotaBy.Homepage.FooterElement;
import PageObject.RabotaBy.Homepage.HeaderElement;
import PageObject.RabotaBy.Homepage.MainElement;
import PageObject.RabotaBy.SearchResultPage.SearchRoot;
import org.testng.annotations.Test;

public class HomePage_Test extends BaseTest {

    @Test(priority = 1)
    public void checkMainPage_Test(){
        get(MainElement.class).open().checkMainElement();
        get(HeaderElement.class).checkHeaderElement();
        get(FooterElement.class).checkFooterElement();
        get(DashboardElement.class).checkDashboardElement();
        get(MainElement.class).checkMainElement().enterSearch("Automation QA");
    }

    @Test(priority = 1)
    public void checkSearchPage_Test(){
        get(SearchRoot.class).checkSearchRoot();
        get(SearchRoot.class).checkSearchResult("Automation QA",1);
    }
}
