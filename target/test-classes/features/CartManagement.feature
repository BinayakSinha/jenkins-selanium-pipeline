@Regression @AmazonE2E @Cart
Feature: Amazon Cart Management
  As a customer, I want to add items to my cart and verify its contents.

  Background: 
    Given the user is on the Amazon homepage

  Scenario: Add an item to the shopping cart
    When the user searches for "Gaming Mouse"
    And adds the first available item to the cart
    Then the cart badge count should increase

  @Smoke
  Scenario: Empty Cart Validation
    When the user clicks on the shopping cart icon
    Then the cart should display an empty cart message