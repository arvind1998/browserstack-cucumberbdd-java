Feature: Automatically correct mistyped search terms

  Scenario: Enter search term and view related images-6
    Given I am on the website 'http://www.google.com'
    When I submit the search term 'BrowserStack'
    Then the page title should be 'BrowserStack - Google Search'



  Scenario: Enter search term and view related images-7
    Given I am on the website 'http://www.google.com'
    When I submit the search term 'google'
    Then the page title should be 'google - Google Search'





