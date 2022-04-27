package CucumberSteps.Booking;

import BaseObjects.BaseTest;
import PageObject.booking.SearchResultsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Search_Steps extends BaseTest {

    @Given("i load search page")
    public void openSearchPage() {
        get(SearchResultsPage.class).open();
    }

    @When("i enter hotel name {string}")
    public void enterHotelName(String hotelName) {
        get(SearchResultsPage.class).closeCalendar()
                .enterHotelName(hotelName);
    }

    @And("click on Search button")
    public void clickSearchButton() {
        get(SearchResultsPage.class).clickSearchButton();
    }

    @Then("list of searched hotels is opened")
    public void openResults() {
        get(SearchResultsPage.class).checkResultMessage();
    }

    @Then("i compare the number of stars {int} of hotel {string}")
    public void compareTheNumberOfStars(int numberOfStars,String hotelName) {
        get(SearchResultsPage.class).compareTheNumberOfStars(numberOfStars,hotelName);
    }

}



