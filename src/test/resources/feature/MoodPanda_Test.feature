
Feature: MoodPanda test

  Background: Open login page
    Given i load search page
    When i click get started

  Scenario Outline: Check login page
    When i enter user "<Username>"
    When i enter password "<UserPassword>"
    When click on login button
    Then check login exception
    Then i close
    Examples:
      | Username | UserPassword |
      | user 1   | 111          |
      | user 2   | 222          |
