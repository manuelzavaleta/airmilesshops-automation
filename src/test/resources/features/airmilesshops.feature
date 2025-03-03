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
    Then the user should see the correct "APPAREL" page header
    And the user should see store cards displayed on the page:
      | adidas |
      | ALDO   |

  Scenario: Verify search results and store info for Amazon
    Given the user is on the home page
    When the user searches for "Amazon"
    And the user clicks on the search button
    Then the user should see results for "Amazon"
    And the "Amazon" store card should be displayed
    When the user hovers on the "Amazon" store card
    And the user selects Store Info from "Amazon" store card
    Then the user should see the correct "AMAZON.CA" page header
    And store info page should display Shop Now button
    And store info page should display Favourite button
