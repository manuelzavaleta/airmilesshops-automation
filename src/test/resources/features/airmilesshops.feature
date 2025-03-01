Feature: Visit a webpage

  Scenario: Open example
    Given I open the browser
    When I visit "https://uat.airmilesshops.ca/"
    Then I should see the page title as "Shop online. Get Miles. 250+ online stores | airmilesshops.ca | airmilesshops.ca"