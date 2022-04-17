Feature: Booking test

  Background: Open search page
    Given i load search page

  Scenario Outline: Check search results
    When i enter hotel name "<Hotelname>"
    And click on Search button
    Then list of searched hotels is opened
    Then i compare the number of stars <NumberOfStars> of hotel "<Hotelname>"
    Examples:
      | Hotelname         | NumberOfStars |
      | Hotel Green Glass | 3             |