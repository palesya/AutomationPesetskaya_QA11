package PageObject.RabotaBy.ContentPage.HomePageContent;

import PageObject.BasePage;

public class HomePage extends BasePage {

    public HomePage open(){
        super.open();
        return this;
    }

    public DashboardElement getDashboardElement(){
        return new DashboardElement();
    }

    public MainElement getMainElement(){
        return new MainElement();
    }
}
