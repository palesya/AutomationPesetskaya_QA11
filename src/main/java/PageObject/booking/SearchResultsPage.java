package PageObject.booking;

import PageObject.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

@Log4j
public class SearchResultsPage extends BasePage {
    private By searchButton = By.xpath("//form[@method='GET']//*[@type='submit']");
    private By totalDashboard = By.xpath("//input[@name='ss']");
    private By resultsMessage = By.tagName("h1");
    private By hotelNames = By.xpath("//*[@data-testid='title']");
    private By star = By.xpath("//*[@data-testid='rating-stars']/span");

    public SearchResultsPage closeCalendar() {
        clickButton(totalDashboard);
        log.debug("Calendar closed.");
        return this;
    }

    public SearchResultsPage enterHotelName(String hotelName) {
        enter(totalDashboard, hotelName);
        log.debug("Hotel name entered: " + hotelName);
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        clickButton(searchButton);
        log.debug("Search button clicked.");
        return this;
    }

    public SearchResultsPage checkResultMessage() {
        Assert.assertTrue(getText(resultsMessage).contains("found"));
        return this;
    }

    public SearchResultsPage compareTheNumberOfStars(int numberOfStars, String hotelName) {
        List<WebElement> text = getWebElements(hotelNames);
        boolean nameFound = false;
        WebElement hotel = null;
        for (WebElement element : text) {
            if (Objects.equals(element.getText(), hotelName)) {
                nameFound = true;
                hotel = element;
            }
        }
        Assert.assertTrue(nameFound);
        int numberOfFoundStars = driver.findElements(with(star).near(hotel,80)).size();
        System.out.println("------------------------------------------------");
        System.out.println(numberOfFoundStars);
        Assert.assertEquals(numberOfFoundStars, numberOfStars);
        return this;
    }

}
