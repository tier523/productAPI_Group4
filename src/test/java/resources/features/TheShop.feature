Feature: The Shop

  Scenario: The Shop should show correct title
    Given The Shop is available
    When User visits The Shop
    Then The title should be "The Shop"
