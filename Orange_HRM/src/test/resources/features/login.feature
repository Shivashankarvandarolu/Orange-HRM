Feature: Login Page UI validation
  As a user
  I want to verify that login page elements are visible
  So that I can enter credentials correctly

	@login_test
  Scenario: Verify that the all the fields is visible on the login page
   ## Given I launch the browser
    When I navigate to the login page
    Then I should see the email textbox and password textbox displayed
    When I enter email 'Admin' and password 'admin123'
    Then I click on login button
## Test