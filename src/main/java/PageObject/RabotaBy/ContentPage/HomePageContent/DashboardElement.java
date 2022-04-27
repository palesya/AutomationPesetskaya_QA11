package PageObject.RabotaBy.ContentPage.HomePageContent;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardElement extends HomePage {

    private By totalDashboard= By.xpath("//*[@class='supernova-dashboard-content']");

    public DashboardElement checkDashboardContent(){
        Assert.assertTrue(isElementExists(totalDashboard));
        return this;
    }
}
