Feature: Home Page Navigation and Search

  Scenario: Verify subcategories under Apparel and store cards
    Given the user is on the home page
    When the user hovers over Browse Categories
    And the user hovers over Apparel
    Then the user should see all subcategories:
      | All Apparel      |
      | Women's Clothing |
      | Men's Clothing   |
      | Kids' Clothing   |
      | Shoes            |
      | Accessories      |
    When the user clicks on the All Apparel link
    Then the user should see the correct page header
    And the user should see store cards displayed on the page:
      | adidas |
      | ALDO   |
