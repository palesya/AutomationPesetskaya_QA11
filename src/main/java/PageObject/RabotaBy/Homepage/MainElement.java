package PageObject.RabotaBy.Homepage;

import PageObject.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MainElement extends BasePage {

    private By totalMain = By.id("HH-React-Root");
    private By searchInput = By.xpath("//input[@data-qa='search-input']");
    private By submitButton = By.xpath("//*[@class='supernova-search-submit-text']");

    public MainElement open(){
        super.open();
        return this;
    }

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
