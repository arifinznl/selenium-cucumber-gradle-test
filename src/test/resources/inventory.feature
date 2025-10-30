@inventory
Feature: Inventory Page

  @view-products
  Scenario: User can see list of products on inventory page
    Given user is on login page
    And user input username with "standard_user"
    And user input password with "secret_sauce"
    When user click login button
    Then user is on inventory page
    And user can see product list

  @add-to-cart
  Scenario: User can add product to cart
    Given user is logged in successfully
    When user add product "Sauce Labs Backpack" to cart
    Then cart badge should show "1"

  @remove-from-cart
  Scenario: User can remove product from cart
    Given user has added product "Sauce Labs Backpack" to cart
    When user remove product "Sauce Labs Backpack"
    Then cart badge should not be visible

  @add-multiple-products
  Scenario: Add multiple products to cart
    Given user is logged in successfully
    When user add product "Sauce Labs Backpack" to cart
    And user add product "Sauce Labs Bike Light" to cart
    Then cart badge should show "2"

  @add-multiple-products-remove-one
  Scenario: Add multiple products then remove one
    Given user is logged in successfully
    When user add product "Sauce Labs Backpack" to cart
    And user add product "Sauce Labs Bike Light" to cart
    And user remove product "Sauce Labs Backpack"
    Then cart badge should show "1"