Feature: The Shop

  Scenario: The Shop should show correct title
    Given The Shop is available
    When User visits The Shop
    Then The title should be "The Shop"


  Scenario: Check cart counter is empty when page loads first
    Given The user is on the webshop homepage for the first time
    Then The cart counter should be empty


  Scenario: Add item to cart
    Given User wants to add item to cart
    When Item is added to the cart
    Then The cart size should be 1