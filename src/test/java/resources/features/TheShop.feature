Feature: The Shop

  //Tim
  Scenario: The Shop should show correct title
    Given The Shop is available
    When User visits The Shop
    Then The title should be "The Shop"


  Scenario: Check cart counter is empty when page loads first //Nafisa
    Given The user is on the webshop homepage for the first time
    Then The cart counter should be empty


  Scenario: Add item to cart
    Given User wants to add item to cart
    When Item is added to the cart
    Then The cart size should be 1


  Scenario: Verify product categories display  //Beata
    Given The user is on the webshop homepage for the first time
    When The user clicks on the all products button
    Then The top section of the page should display all product categories


  Scenario: Remove item from cart  //Beata
    Given The user adds 2 items to the cart
    When The user removes one item from the cart
    Then The cart should only contain the remaining item


  Scenario: Check search box function  //Nafisa
    Given The user is on the webshop homepage
    When User clicks on the header menu 'Shop'
    Then User searches 'acer' in the search box


  Scenario: Navigate using the web menu //Elin
    Given The user wants to navigate
    When The user clicks on the header menu 'About'
    Then The user should be taken to the 'About' page


  Scenario: Check invalid first name feedback on checkout form //Nafisa
    Given The user clicks Checkout button on homepage
    When The user clicks continue to checkout button
    Then The user can see invalid feedback for first name on checkout form


  Scenario: Search works with different letter cases  //Beata
    Given The user is on the webshop homepage
    When User clicks on the header menu 'Shop'
    And User searches for "ACER"
    And User searches for "acer"
    And User searches for "Acer"
    Then The search result should be the same for all cases
