@Regression @StoreE2E
Feature: E-Commerce Real Scenarios (Demoblaze)
  As a customer, I want to navigate the store, manage my cart, and handle authentication.

  Background: 
    Given the user is on the store homepage

  @Smoke @Navigation
  Scenario: 1. Category Filtering
    When the user clicks on the "Laptops" category
    Then the product list should update to show laptops

  @Cart
  Scenario: 2. Add an item to the shopping cart
    When the user clicks on the first available product
    And adds the item to the cart
    Then a confirmation alert should appear

  @Cart
  Scenario: 3. Cart Total Validation
    # We must add an item first to ensure a total exists to calculate
    When the user clicks on the first available product
    And adds the item to the cart
    # Now we can check the cart
    And the user navigates to the Cart page
    Then the cart total should be calculated successfully

  @Negative @Auth
  Scenario: 4. Invalid Login Attempt
    When the user navigates to the login modal
    And enters the username "fake_user_999" and password "wrong_pass"
    Then an alert should indicate the user does not exist

  @Navigation @Smoke
  Scenario: 5. Contact Form Submission
    When the user opens the contact message form
    And fills out the message details
    Then a confirmation alert should acknowledge the message