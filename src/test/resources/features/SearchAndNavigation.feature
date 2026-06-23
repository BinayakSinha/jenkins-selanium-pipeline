@Regression @AmazonE2E @Search @Navigation
Feature: Amazon Product Search and Navigation
  As a customer, I want to search for products and navigate the site menus.

  Background: 
    Given the user is on the Amazon homepage

  @Smoke
  Scenario: Basic Search Functionality
    When the user searches for "Mechanical Keyboard"
    Then the search results page should display results for "Mechanical Keyboard"

  Scenario: Navigate to Today's Deals
    When the user clicks on the Today's Deals menu
    Then the Today's Deals page should load successfully