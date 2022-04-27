package PageObject.RabotaBy.ContentPage.HomePageContent;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MainElement extends HomePage {

    private By totalMain = By.id("HH-React-Root");
    private By searchInput = By.xpath("//input[@data-qa='search-input']");
    private By submitButton = By.xpath("//*[@class='supernova-search-submit-text']");

    public MainElement checkMainElement(){
        Assert.assertTrue(isElementExists(totalMain));
        return this;
    }

    public MainElement enterSearch(String searchText){
        enter(searchInput,searchText);
        clickButton(submitButton);
        return this;
    }

}
