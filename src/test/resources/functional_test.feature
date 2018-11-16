Feature: functional_test


  Scenario: want to log and create 35 apps
    Given that i am on the website
    When i create an account
    When i log2
    When I am on my page i create 25 apps
    When I am on my page i scroll throught 3 pages
    When i am on the login page i try to access a forbiden page
    Then i am still on login page