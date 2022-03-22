package PageObject.RabotaBy.Homepage;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardElement extends BasePage {

    private By totalDashboard= By.xpath("//*[@class='supernova-dashboard-content']");

    public DashboardElement checkDashboardElement(){
        Assert.assertTrue(isElementExists(totalDashboard));
        return this;
    }
}
