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
    private By calendar = By.xpath("//*[@class='bui-calendar']");
    private By searchButton = By.xpath("//form[@method='GET']//*[@type='submit']");
    private By totalDashboard = By.xpath("//input[@name='ss']");
    private By resultsMessage = By.tagName("h1");
    private By hotelNames = By.xpath("//*[@data-testid='title']");
    private By star = By.xpath("//*[@data-testid='rating-stars']/span");
    private By listOfHotels = By.xpath("//ul/li/div[@role='button']");

    public SearchResultsPage closeCalendar() {
        int numberOfShownCalendars = findElementsCount(calendar);
        System.out.println("----------------------" + numberOfShownCalendars);
        if (numberOfShownCalendars > 0) {
            clickButton(totalDashboard);
            waitForElementToDisappear(calendar);
            log.debug("Calendar closed.");
        } else {
            log.debug("No calendar was shown.");
        }
        return this;
    }

    public SearchResultsPage enterHotelName(String hotelName) {
        waitForElementToAppear(totalDashboard);
        enter(totalDashboard, hotelName);
        waitForElementToAppear(listOfHotels);
        clickButton(listOfHotels);
        log.debug("Hotel name entered: " + hotelName);
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        waitForElementToAppear(searchButton);
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
        scrollDown();
        for (WebElement element : text) {
            if (Objects.equals(element.getText(), hotelName)) {
                nameFound = true;
                hotel = element;
            }
        }
        Assert.assertTrue(nameFound);
        int numberOfFoundStars = driver.findElements(with(star).near(hotel, 80)).size();
        System.out.println("------------------------------------------------");
        System.out.println(numberOfFoundStars);
        Assert.assertEquals(numberOfFoundStars, numberOfStars);
        return this;
    }

}
