Feature: The Shop

  Scenario: The Shop should show correct title
    Given The Shop is available
    When User visits The Shop
    Then The title should be "The Shop"


  Scenario: Add item to cart
    Given User wants to add item to cart
    When Item is added to the cart
    Then The cart size should be 1